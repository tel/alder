package jspha.sol.internal

/**
  * Typeclass describing how a value gets transformed into a CSS-value, i.e.
  * a string.
  *
  * TODO: Add context around the current browser so that each value can make
  * a choice about how it out to represent itself.
  *
  * TODO: Return a Seq instead of a single value so that multiple
  * (potentially fallback) values can be generated.
  */
trait CssValue[A] {
  def cssRepr(self: A): String
}

object CssValue {


  def apply[A](a: A)(implicit ev: CssValue[A]): String =
    ev.cssRepr(a)

  def fromToString[A] = new CssValue[A] {
    def cssRepr(a: A) = a.toString
  }

  implicit val intIsCssValue = fromToString[Int]
  implicit val doubleIsCssValue = fromToString[Double]

  case class Literal(lit: String)

  implicit val literalIsCssValue = new CssValue[Literal] {
    def cssRepr(self: Literal) = self.lit
  }

  case class CssList[A](seq: Seq[A])

  implicit def sequenceIsCssValue[A: CssValue] = new CssValue[CssList[A]] {
    def cssRepr(self: CssList[A]) =
      self.seq.map(CssValue(_)).mkString(" ")
  }
}
