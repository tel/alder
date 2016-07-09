package jspha.atelier.css

import jspha.atelier.internal.Document

/**
  * A value of type `Selector` describes a CSS selector statement.
  */
sealed trait Selector

object Selector {

  case class Element(element: String, ns: Option[Namespace] = None)
    extends Selector

  case class Id(id: String) extends Selector

  case class Class(cls: String) extends Selector

  implicit object PrintSelector extends Print[Selector] {

    import Document._

    def doc(a: Selector) = a match {
      case Element(element, may) => may match {
        case None => text(element)
        case Some(ns) => Print[Namespace].doc(ns) ++ "|" ++ element
      }
      case Id(id) => text("#") ++ text(id)
      case Class(cls) => text(".") ++ text(cls)
    }
  }

}