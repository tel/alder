package jspha.atelier.css

import jspha.atelier.internal.Doc

sealed trait Decl

object Decl {

  case class Selection(selector: Selector, style: Rules) extends Decl

  case class Media(mediaQuery: MediaQuery, css: Sheet) extends Decl

  case class FontFamily(rules: Rules) extends Decl

  case class Keyframes(iden: String, desc: Map[String, Rules]) extends Decl

  implicit object PrintDecl extends Print[Decl] {

    import Doc._

    def doc(block: Decl) = block match {
      case Selection(selector, style) =>
        rec(selector) <+> braced(rec(style))

      case Media(mq, css) =>
        "@media" <+> rec(mq) <+> braced(rec(css))

      case FontFamily(rules) =>
        "@font-family" <+> braced(rec(rules))

      case Keyframes(iden, desc) =>
        val dstrings = desc.map {
          case (pct, rules) => pct <+> "{" <+> rec(rules) <+> "}"
        }(collection.breakOut)

        "@keyframes" <+> iden <+> braced(lines(dstrings))

    }
  }

}
