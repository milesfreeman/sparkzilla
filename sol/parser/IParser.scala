package sparkzilla.sol.parser

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

trait IParser {

  /**
   * Parses an html page
   *
   * @return a parse tree representing the html page
   * @throws IOException if a problem occurs while reading from file
   * @throws LexicalException if a problem occurs while tokenizing
   * @throws ParseException if a problem occurs while parsing
   */
  def parse(): HTMLElem

}

object IParser {

  /**
   * An exception thrown when there is an unexpected tokenizer.
   *
   * @param expected - the expected token
   * @param actual - the actual token
   */
  class ParseException(expected: String, actual: Token)
    extends Exception("Expected " + expected + ", but instead found " + actual + ".") {

    def this(expected: String) {
      this(expected, Eof)
    }
  }

}
