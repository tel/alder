package jspha.atelier.css

import jspha.atelier.internal.{DString, Document}

sealed trait Decl

object Decl {

  case class Selection(selector: Selector, style: Rules) extends Decl

  case class Media(mediaQuery: MediaQuery, css: CSS) extends Decl

  case class FontFamily(rules: Rules) extends Decl

  case class Keyframes(iden: String, desc: Map[String, Rules]) extends Decl

  implicit object PrintDecl extends Print[Decl] {

    import Document._

    def doc(block: Decl) = block match {
      case Selection(selector, style) =>
        Print[Selector].doc(selector) ++ "{" +/+ Print[Rules].doc(style) +/+ "}"

      case Media(mq, css) =>
        text("@media") ++
          Print[MediaQuery].doc(mq) ++
          "{" +/+ Print[CSS].doc(css) +/+ "}"

      case FontFamily(rules) =>
        text("@font-family") ++ "{" +/+ Print[Rules].doc(rules) +/+ "}"

      case Keyframes(iden, desc) =>
        val dstrings = desc.map {
          case (pct, rules) =>
            text(pct) ++ "{" +/+ Print[Rules].doc(rules) +/+ "}"
        }(collection.breakOut)

        text("@keyframes") ++
          text(iden) ++
          "{" +/+ lines(dstrings) +/+ "}"

    }
  }

}
