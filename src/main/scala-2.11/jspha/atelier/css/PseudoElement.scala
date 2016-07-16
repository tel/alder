package jspha.atelier.css

sealed trait PseudoElement

object PseudoElement {
  case object After extends PseudoElement
  case object Before extends PseudoElement
  case object FirstLetter extends PseudoElement
  case object FirstLine extends PseudoElement
  case object Selection extends PseudoElement
  case object Backdrop extends PseudoElement

  implicit object PrintPseudoElement extends Print[PseudoElement] {
    import jspha.atelier.internal.Doc
    import Doc._

    def doc(a: PseudoElement): Doc = a match {
      case After => "::after"
      case Before => "::before"
      case FirstLetter => "::first-letter"
      case FirstLine => "::first-line"
      case Selection => "::selection"
      case Backdrop => "::backdrop"
    }
  }
}


