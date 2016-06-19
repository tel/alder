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

  case class Rgba(r: Int,
                  g: Int,
                  b: Int,
                  a: Option[Double]) extends Color {

    def toHsla: Hsla = {
      import Math._

      val rd: Double = r/255
      val gd: Double = g/255
      val bd: Double = b/255

      val outer = max(rd, max(gd, bd))
      val inner = min(rd, min(gd, bd))

      val lightness = (outer + inner) / 2
      val bandwidth = outer - inner

      if (bandwidth == 0)
        Hsla(0, 0, lightness, a) // achromatic
      else {
        val saturation =
          if (lightness > 0.5)
            bandwidth / (2 - outer - inner)
          else
            bandwidth / (outer + inner)

        val hueSix =
          outer match {
            case _ if outer == rd =>
              (gd - bd) / bandwidth + (if (gd < bd) 6 else 0)
            case _ if outer == gd =>
              (bd - rd) / bandwidth + 2
            case _ if outer == bd =>
              (rd - gd) / bandwidth + 4
          }

        Hsla(hueSix / 6, saturation, lightness, a)
      }
    }
  }

  case class Hsla(h: Double,
                  s: Double,
                  l: Double,
                  a: Option[Double]) extends Color {

   private  def toWord(x: Double): Int =
      Math.floor(x * 255).toInt

    def toRgba: Rgba = {
      import Math._

      val chroma = (1 - abs(2 * l - 1)) * s
      val hueSix = h / 60
      val x = chroma * (1 - abs(hueSix % 2 - 1))
      val m = l - 0.5 * chroma

      val v1 = toWord(chroma + m)
      val v2 = toWord(x + m)
      val v3 = toWord(m)

      if      (0 <= hueSix && hueSix < 1)
        Rgba(v1, v2, v3, a)
      else if (1 <= hueSix && hueSix < 2)
        Rgba(v2, v1, v3, a)
      else if (2 <= hueSix && hueSix < 3)
        Rgba(v3, v1, v2, a)
      else if (3 <= hueSix && hueSix < 4)
        Rgba(v3, v2, v1, a)
      else if (4 <= hueSix && hueSix < 5)
        Rgba(v2, v3, v1, a)
      else // if (5 <= hueSix && hueSix < 6)
        Rgba(v1, v3, v2, a)
    }
  }

  implicit val isCssValue = new CssValue[Color] {
    def cssRepr(color: Color) = color match {
      case Rgba(r, g, b, Some(a)) => s"rgba($r, $g, $b, $a)"
      case Rgba(r, g, b, None) => s"rgb($r, $g, $b)"
      case Hsla(h, s, l, Some(a)) => s"hsla($h, $s, $l, $a)"
      case Hsla(h, s, l, None) => s"hsl($h, $s, $l)"
    }
  }

  def apply(r: Int, g: Int, b: Int): Color = rgb(r, g, b)
  def apply(r: Int, g: Int, b: Int, a: Double): Color = rgba(r, g, b, a)

  def hex(s: String): Color = {
    if (s.length <= 3) P.rgbOfHex3(s) else P.rgbOfHex6(s)
  }

  def hsl(h: Double, s: Double, l: Double) =
    Hsla(
      h = P.normalizeDegrees(h),
      s = P.clampPct(s).getOrElse(1.0),
      l = P.clampPct(l).getOrElse(1.0),
      a = None
    )

  def hsla(h: Double, s: Double, l: Double, a: Double) =
    Hsla(
      h = P.normalizeDegrees(h),
      s = P.clampPct(s).getOrElse(1.0),
      l = P.clampPct(l).getOrElse(1.0),
      a = P.clampPct(a)
    )

  def rgb(r: Int, g: Int, b: Int): Color =
    Rgba(
      r = P.clampWord(r),
      g = P.clampWord(g),
      b = P.clampWord(b),
      a = None
    )

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

    def normalizeDegrees(deg: Double): Double = {
      import Math._

      val wraps = deg / 360

      if (wraps > 0)
        deg - 360 * floor(wraps)
      else
        deg + 360 * ceil(abs(wraps))

    }

    def clampWord(it: Int): Int =
      it.max(0).min(255)

    def clampPct(it: Double): Option[Double] =
      if (it >= 1) None else Some(it.max(0))

  }

}
