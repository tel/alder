package jspha.atelier.css

import scala.collection.breakOut
import jspha.atelier.internal.DString

/**
  * Values of type `Style` represent a set of style constraints applied to a
  * particular element.
  */
case class Style(rules: Map[String, String])

object Style {

  implicit object PrintStyle extends Print[Style] {

    def print(a: Style) =
      DString.concat(
        a.rules.map {
          case (key, value) => DString.constant(s"$key: $value")
        }(breakOut),
        sep = ";"
      )
  }

}

