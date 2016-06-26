package jspha.sol.dsl

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

  def apply[A: CssValue]: CssValue[A] =
    implicitly

  def of[A: CssValue](a: A) =
    apply[A].cssRepr(a)

  def fromToString[A] = new CssValue[A] {
    def cssRepr(a: A) = a.toString
  }

  implicit val nothingIsCssValue = new CssValue[Nothing] {
    def cssRepr(self: Nothing) =
      sys.error("Received value of Nothing when computing a CssValue.")
  }
  implicit val intIsCssValue = fromToString[Int]
  implicit val doubleIsCssValue = fromToString[Double]

  /**
    * CSS lists are space separated
    */
  case class CssList[A](seq: Seq[A])

  implicit def sequenceIsCssValue[A: CssValue] = new CssValue[CssList[A]] {
    def cssRepr(self: CssList[A]) =
      self.seq.map(CssValue.of(_)).mkString(" ")
  }

  object CssList {
    def of[A: CssValue](as: A*): String = CssValue.of(CssList(as))
  }
}
