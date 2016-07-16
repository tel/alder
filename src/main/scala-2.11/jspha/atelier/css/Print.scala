package jspha.atelier.css

import scala.language.implicitConversions
import jspha.atelier.internal.Doc

/**
  * Typeclass describing types which can be printed as CSS.
  */
trait Print[A] {
  def doc(a: A): Doc
  def print(a: A): String =
    doc(a).build

  /**
    * Often we want to recur into another printable type when defining how to
    * print a given type.
    */
  final protected def rec[A: Print](a: A) = Print[A].doc(a)
}

object Print {
  final def apply[A](implicit ev: Print[A]): Print[A] = ev
  final def doc[A](a: A)(implicit ev: Print[A]): Doc = ev.doc(a)
}
