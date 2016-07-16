package jspha.atelier.css

trait PseudoClass

object PseudoClass {

  case class Not(pc: PseudoClass) extends PseudoClass

  case class Lang(langCode: String) extends PseudoClass

  case class NthChild(a: Int, b: Int = 0) extends PseudoClass

  case class NthLastChild(a: Int, b: Int = 0) extends PseudoClass

  case class NthLastOfType(a: Int, b: Int = 0) extends PseudoClass

  case class NthOfType(a: Int, b: Int = 0) extends PseudoClass

  case object Active extends PseudoClass

  case object Any extends PseudoClass

  case object Checked extends PseudoClass

  case object Default extends PseudoClass

  case object DirLtr extends PseudoClass

  case object DirRtl extends PseudoClass

  case object Disabled extends PseudoClass

  case object Empty extends PseudoClass

  case object Enabled extends PseudoClass

  case object First extends PseudoClass

  case object FirstChild extends PseudoClass

  case object FirstOfType extends PseudoClass

  case object Fullscreen extends PseudoClass

  case object Focus extends PseudoClass

  case object Hover extends PseudoClass

  case object Indeterminate extends PseudoClass

  case object InRange extends PseudoClass

  case object Invalid extends PseudoClass

  case object LastChild extends PseudoClass

  case object LastOfType extends PseudoClass

  case object Left extends PseudoClass

  case object Link extends PseudoClass

  case object OnlyChild extends PseudoClass

  case object OnlyOfType extends PseudoClass

  case object Optional extends PseudoClass

  case object OutOfRange extends PseudoClass

  case object ReadOnly extends PseudoClass

  case object ReadWrite extends PseudoClass

  case object Required extends PseudoClass

  case object Right extends PseudoClass

  case object Root extends PseudoClass

  case object Scope extends PseudoClass

  case object Target extends PseudoClass

  case object Valid extends PseudoClass

  case object Visited extends PseudoClass

  implicit object PrintPseudoClass extends Print[PseudoClass] {

    import jspha.atelier.internal.Doc
    import Doc._

    def abDoc(a: Int, b: Int): Doc = {
      s"${a.toString}n+${b.toString}"
    }

    def doc(pc: PseudoClass): Doc = pc match {
      case Not(subc) => ":not(" <> rec(subc) <> ")"
      case Lang(code) => ":lang(" <> code <> ")"
      case NthChild(a, b) => ":nth-child(" <> abDoc(a, b) <> ")"
      case NthLastChild(a, b) => ":nth-last-child(" <> abDoc(a, b) <> ")"
      case NthLastOfType(a, b) => ":nth-last-of-type(" <> abDoc(a, b) <> ")"
      case NthOfType(a, b) => ":nth-of-type(" <> abDoc(a, b) <> ")"

      case Active => ":active"
      case Any => ":any"
      case Checked => ":checked"
      case Default => ":default"
      case DirLtr => ":dir(ltr)"
      case DirRtl => ":dir(rtl)"
      case Disabled => ":disabled"
      case Empty => ":empty"
      case Enabled => ":enabled"
      case First => ":first"
      case FirstChild => ":first-child"
      case FirstOfType => ":first-of-type"
      case Fullscreen => ":fullscreen"
      case Focus => ":focus"
      case Hover => ":hover"
      case Indeterminate => ":indeterminate"
      case InRange => ":in-range"
      case Invalid => ":invalid"
      case LastChild => ":last-child"
      case LastOfType => ":last-of-type"
      case Left => ":left"
      case Link => ":link"
      case OnlyChild => ":only-child"
      case OnlyOfType => ":only-of-type"
      case Optional => ":optional"
      case OutOfRange => ":out-of-range"
      case ReadOnly => ":read-only"
      case ReadWrite => ":read-write"
      case Required => ":required"
      case Right => ":right"
      case Root => ":root"
      case Scope => ":scope"
      case Target => ":target"
      case Valid => ":valid"
      case Visited => ":visited"
    }
  }

}
