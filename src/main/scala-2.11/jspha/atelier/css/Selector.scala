package jspha.atelier.css

/**
  * A value of type `Selector` describes a CSS selector statement.
  */
sealed trait Selector

object Selector {

  case class Element(element: String, ns: Option[Namespace]) extends Selector

  case class Id(id: String) extends Selector

  case class Class(cls: String) extends Selector

  implicit object PrintSelector extends Print[Selector] {
    def print(a: Selector) = a match {
      case Element(element, may) => may match {
        case None => element
        case Some(ns) => s"${Print[Namespace].print(ns)}|$element"
      }
      case Id(id) => s"#$id"
      case Class(cls) => s".$cls"
    }
  }

}