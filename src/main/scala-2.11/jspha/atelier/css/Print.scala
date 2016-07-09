package jspha.atelier.css


import scala.language.implicitConversions
import jspha.atelier.internal.Document

/**
  * Typeclass describing types which can be printed as CSS.
  */
trait Print[A] {
  def doc(a: A): Document
  def print(a: A): String =
    doc(a).mkString
}

object Print {
  def apply[A](implicit ev: Print[A]): Print[A] = ev
}
