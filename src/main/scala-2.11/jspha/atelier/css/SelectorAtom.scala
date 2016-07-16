package jspha.atelier.css

import jspha.atelier.internal.Doc

/**
  * A value of type `Selector` describes a CSS selector statement.
  */
sealed trait SelectorAtom

object SelectorAtom {

  case class Element(element: String, ns: Option[Namespace] = None)
    extends SelectorAtom

  case class Universal(ns: Option[Namespace] = None) extends SelectorAtom

  case class Id(id: String) extends SelectorAtom

  case class Class(cls: String) extends SelectorAtom

  case class Attribute(sel: SelectorAtom, op: AttrOp, insensitive: Boolean = false)
    extends SelectorAtom


  implicit object PrintSelectorAtom extends Print[SelectorAtom] {

    import Doc._

    def doc(a: SelectorAtom) = a match {
      case Element(el, None) => el
      case Element(el, Some(ns)) => rec(ns) <> "|" <> el
      case Universal(None) => "*"
      case Universal(Some(ns)) => rec(ns) <> "|*"
      case Id(id) => "#" <> id
      case Class(cls) => "." <> cls
      case Attribute(sel, op, insensitive) =>
        rec(sel) <> "[" <> rec(op) <> (if (insensitive) " i]" else rbracket)
    }
  }

}