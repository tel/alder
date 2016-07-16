package jspha.atelier.css
import jspha.atelier.internal.Doc

sealed trait AttrOp

object AttrOp {

  case class Present(name: String) extends AttrOp

  case class Equal(name: String, value: String) extends AttrOp

  case class Contains(name: String, value: String) extends AttrOp

  case class SubcodeMatch(name: String, value: String) extends AttrOp

  case class Prefixed(name: String, prefix: String) extends AttrOp

  case class Suffixed(name: String, suffix: String) extends AttrOp

  case class Substring(name: String, value: String) extends AttrOp


  implicit object PrintAttrOp extends Print[AttrOp] {

    import Doc._

    def doc(a: AttrOp): Doc = a match {
      case Present(name) => name
      case Equal(name, value) => name <> "=" <> value
      case Contains(name, value) => name <> "~=" <> value
      case SubcodeMatch(name, value) => name <> "|=" <> value
      case Prefixed(name, prefix) => name <> "^=" <> prefix
      case Suffixed(name, suffix) => name <> "$=" <> suffix
      case Substring(name, value) => name <> "*=" <> value
    }

  }

}
