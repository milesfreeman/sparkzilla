package sparkzilla.sol.parser

import abs._

abstract class HTMLElem {
  def toExplicit: List[AbsElem]
}

case class BeginForm(f: Form, h: HTMLElem) extends HTMLElem {
  override def toExplicit = AbsForm(f.toAbsSeq) :: h.toExplicit
}

case class BeginParagraph(p: Paragraph, h: HTMLElem) extends HTMLElem {
  override def toExplicit = AbsParagraph(p.toAbsSeq) :: h.toExplicit
}

case object EndHTMLElem extends HTMLElem {
  override def toExplicit = List[AbsElem]()
}
