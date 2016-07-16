package jspha.atelier.css

import scala.collection.breakOut
import jspha.atelier.internal.Doc

/**
  * Values of type `Rules` represent a set of style constraints applied to a
  * particular element.
  */
case class Rules(rules: Map[String, String])

object Rules {

  implicit object PrintStyle extends Print[Rules] {

    import Doc._

    def doc(a: Rules) = {
      val rules = a.rules.map {
        case (key, value) => key <> ":" <+> value
      }(breakOut)

      intercalate("; ", rules)
    }
  }

}

