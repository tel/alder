package jspha.atelier.css

import scala.collection.breakOut
import jspha.atelier.internal.{DString, Document}

/**
  * Values of type `Rules` represent a set of style constraints applied to a
  * particular element.
  */
case class Rules(rules: Map[String, String])

object Rules {

  implicit object PrintStyle extends Print[Rules] {

    import Document._

    def doc(a: Rules) =
      Document.lines(a.rules.map { case (key, value) =>
        nest(2, text(key) ++ ":" ++ text(value))
      }(breakOut))
  }

}

