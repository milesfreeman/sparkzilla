package sparkzilla.sol.parser.abs

abstract class AbsParagraphElem

case class AbsLink(url: String) extends AbsParagraphElem
case class AbsText(text: String) extends AbsParagraphElem
