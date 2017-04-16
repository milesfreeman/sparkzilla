package sparkzilla.sol.parser

import sparkzilla.sol.parser.IParser.ParseException
import sparkzilla.src.HTMLTokenizer
import sparkzilla.src.Token
import sparkzilla.src.OpenHTML
import sparkzilla.src.CloseHTML
import sparkzilla.src.OpenParagraph
import sparkzilla.src.CloseParagraph
import sparkzilla.src.OpenBody
import sparkzilla.src.CloseBody
import sparkzilla.src.OpenLink
import sparkzilla.src.CloseLink
import sparkzilla.src.Text
import sparkzilla.src.Input
import sparkzilla.src.Submit
import sparkzilla.src.OpenForm
import sparkzilla.src.CloseForm
import sparkzilla.src.Eof

/**
 * A parser for html pages
 *
 * @param tokenizer - a fresh tokenizer (should not have been primed yet)
 *                    ready to tokenize an html page
 */
class Parser(tokenizer: HTMLTokenizer) extends IParser {

  private def current: Option[Token] = tokenizer.current
  private def advance() = tokenizer.advance()

  private def eat(l: Token*) {
    l.foreach(t =>
      current match {
        case Some(t) => advance()
        case _       => throw new ParseException(t.toString)
      })
  }

  override def parse(): HTMLElem = {
    // Prime the tokenizer
    advance()

    // Parse the expression if first 2 tokens are OpenHTML, OpenBody
    eat(OpenHTML, OpenBody)
    val exp = parseHTMLElem()

    // We finished parsing, so throw an exception
    // if we haven't reached CloseBody, CloseHTML, Eof
    eat(CloseHTML, Eof)

    exp
  }

  private def parseHTMLElem(): HTMLElem = current match {
    case Some(OpenForm(url)) =>
      advance()
      new BeginForm(parseForm(url), parseHTMLElem())
    case Some(OpenParagraph) =>
      advance()
      new BeginParagraph(parseParagraph(), parseHTMLElem())
    case Some(CloseBody) =>
      advance()
      EndHTMLElem
    case _ =>
      throw new ParseException("form, paragraph, or end-of-body")
  }

  private def parseForm(url: String): Form = current match {
    case Some(OpenParagraph) =>
      advance()
      new BeginFormParagraph(parseParagraph(), parseForm(url))
    case Some(Input(name)) =>
      advance()
      new BeginInput(name, parseForm(url))
    case Some(Submit) =>
      advance()
      new BeginSubmit(url, parseForm(url))
    case Some(CloseForm) =>
      advance()
      EndForm
    case _ =>
      throw new ParseException("form, paragraph, or end-of-body")
  }

  private def parseParagraph(): Paragraph = current match {
    case Some(Text(t)) =>
      advance()
      new BeginText(t, parseParagraph()) // parseParagraph(), parseForm(url))
    case Some(OpenLink(url)) =>
      advance()
      new BeginLink(url, parseParagraph())
    case Some(CloseParagraph) =>
      advance()
      EndParagraph
    case _ =>
      throw new ParseException("form, paragraph, or end-of-body")
  }

}
