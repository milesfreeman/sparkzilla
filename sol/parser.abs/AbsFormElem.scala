package sparkzilla.sol.parser.abs

abstract class AbsFormElem

case class AbsInput(name: String) extends AbsFormElem
case class AbsSubmit(url: String) extends AbsFormElem
case class AbsFormParagraph(p: AbsParagraph) extends AbsFormElem
