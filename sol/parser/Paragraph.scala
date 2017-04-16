package sparkzilla.sol.parser

import abs._

abstract class Paragraph {
  def toAbsSeq: List[AbsParagraphElem]
}

case class BeginLink(url: String, p: Paragraph) extends Paragraph {
  override def toAbsSeq = AbsLink(url) :: p.toAbsSeq
}

case class BeginText(text: String, p: Paragraph) extends Paragraph {
  override def toAbsSeq = AbsText(text) :: p.toAbsSeq
}

case object EndParagraph extends Paragraph {
  override def toAbsSeq = List[AbsParagraphElem]()
}
