package jspha.atelier.css

import jspha.atelier.internal.DString
import scala.language.implicitConversions

/**
  * Typeclass describing types which can be printed as CSS.
  */
trait Print[A] {
  def print(a: A): DString
}

object Print {
  def apply[A](implicit ev: Print[A]) = ev
}
