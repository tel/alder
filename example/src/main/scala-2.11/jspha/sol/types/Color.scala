package jspha.sol.types

import jspha.sol.internal.CssValue

/**
  * - Supports both RGB(A) and HSL(A)
  * - Does not pass named colors through
  * - Does not pass hex colors through
  *
  * See: https://en.wikipedia.org/wiki/HSL_and_HSV
  */
sealed trait Color

object Color {

  case class Rgba(r: Int, g: Int, b: Int, a: Option[Double]) extends Color

  implicit val isCssValue = new CssValue[Color] {
    def cssRepr(color: Color) = color match {
      case Rgba(r, g, b, Some(a)) => s"rgba($r, $g, $b, $a)"
      case Rgba(r, g, b, None) => s"rgb($r, $g, $b)"
    }
  }

  def apply(r: Int, g: Int, b: Int): Color = rgb(r, g, b)
  def apply(r: Int, g: Int, b: Int, a: Double): Color = rgba(r, g, b, a)

  def hex(s: String): Color = {
    if (s.length <= 3) P.rgbOfHex3(s) else P.rgbOfHex6(s)
  }

  def rgb(r: Int, g: Int, b: Int): Color =
    Rgba(r, g, b, None)

  def rgba(r: Int, g: Int, b: Int, a: Double): Color =
    Rgba(
      r = P.clampWord(r),
      g = P.clampWord(g),
      b = P.clampWord(b),
      a = P.clampPct(a)
    )

  object Names {
    val black = rgb(0, 0, 0)
    val white = rgb(255, 255, 255)
  }

  private object P {

    val hexCharacters: Set[Char] =
      Set(
        '1', '2', '3', '4', '5',
        '6', '7', '8', '9', '0',
        'a', 'b', 'c', 'd', 'e', 'f',
        'A', 'B', 'C', 'D', 'E', 'F'
      )

    def rgbOfHex3(s: String): Color = {
      val canonicalString = (s + "fff")
        .filter(hexCharacters)
        .take(3)
      val rChar = canonicalString(0)
      val gChar = canonicalString(1)
      val bChar = canonicalString(2)

      // #fab --> #ffaabb

      val r = Integer.parseInt(Seq(rChar, rChar).mkString, 16)
      val g = Integer.parseInt(Seq(gChar, gChar).mkString, 16)
      val b = Integer.parseInt(Seq(bChar, bChar).mkString, 16)

      rgb(r, g, b)
    }

    def rgbOfHex6(s: String): Color = {
      val canonicalString = (s + "ffffff") take 6

      val r = Integer.parseInt(canonicalString.slice(0, 2), 16)
      val g = Integer.parseInt(canonicalString.slice(2, 4), 16)
      val b = Integer.parseInt(canonicalString.slice(4, 6), 16)

      rgb(r, g, b)
    }

    def clampWord(it: Int): Int =
      it.max(0).min(255)

    def clampPct(it: Double): Option[Double] =
      if (it >= 1) None else Some(it.max(0))

  }

}
