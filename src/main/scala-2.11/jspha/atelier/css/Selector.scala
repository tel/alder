package jspha.atelier.css

import scala.language.implicitConversions

sealed trait Selector {

  import Selector._

  def +(other: Selector): Selector = Adjacent(this, other)
  def ~(other: Selector): Selector = Sibling(this, other)
  def >(other: Selector) = Child(this, other)
  def >>(other: Selector) = Descendent(this, other)

}

object Selector {

  implicit def atom(a: SelectorAtom): Selector = Atom(a)

  case class Atom(a: SelectorAtom) extends Selector

  case class Adjacent(prev: Selector, y: Selector) extends Selector

  case class Sibling(x: Selector, y: Selector) extends Selector

  case class Child(x: Selector, y: Selector) extends Selector

  case class Descendent(x: Selector, y: Selector) extends Selector

  implicit object PrintSelector extends Print[Selector] {

    import jspha.atelier.internal.Doc._

    def doc(a: Selector) = a match {
      case Atom(a) => rec(a)
      case Adjacent(x, y) => rec(x) <> "+" <> rec(y)
      case Sibling(x, y) => rec(x) <> "~" <> rec(y)
      case Child(x, y) => rec(x) <> ">" <> rec(y)
      case Descendent(x, y) => rec(x) <> " " <> rec(y)
    }

  }
}
