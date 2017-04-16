package sparkzilla.sol.parser

import abs._

abstract class Form {
  def toAbsSeq: List[AbsFormElem]
}

case class BeginFormParagraph(p: Paragraph, f: Form) extends Form {
  override def toAbsSeq = AbsFormParagraph(AbsParagraph(p.toAbsSeq)) :: f.toAbsSeq
}

case class BeginInput(name: String, f: Form) extends Form {
  override def toAbsSeq = AbsInput(name) :: f.toAbsSeq
}

case class BeginSubmit(url: String, f: Form) extends Form {
  override def toAbsSeq = AbsSubmit(url) :: f.toAbsSeq
}

case object EndForm extends Form {
  override def toAbsSeq = List[AbsFormElem]()
}
