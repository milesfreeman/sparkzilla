package sparkzilla.sol.parser.abs

abstract class AbsElem // explicit representation will be a list of these AbsElems

case class AbsForm(list: List[AbsFormElem]) extends AbsElem
case class AbsParagraph(list: List[AbsParagraphElem]) extends AbsElem
