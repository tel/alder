package jspha.annDoc

import scala.{Char => SChar}

/**
  * The data type `SimpleDoc a` represents rendered documents and is
  * used by the display functions.
  *
  * The `Int` in `Text` contains the length of the string. The `Int`
  * in `Line` contains the indentation for that line.
  */
sealed trait SimpleDoc[+A]

object SimpleDoc {

  case object Empty extends SimpleDoc[Nothing]

  case class Char[A](char: SChar, next: SimpleDoc[A]) extends SimpleDoc[A]

  case class Text[A](width: Int, text: String, next: SimpleDoc[A]) extends SimpleDoc[A]

  case class Line[A](indentation: Int, next: SimpleDoc[A]) extends SimpleDoc[A]

  case class AnnotStart[A](ann: A, next: SimpleDoc[A]) extends SimpleDoc[A]

  case class AnnotStop[A](next: SimpleDoc[A]) extends SimpleDoc[A]

}
