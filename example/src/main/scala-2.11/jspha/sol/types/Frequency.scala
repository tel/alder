package jspha.sol.types

import jspha.sol.internal.CssValue

sealed trait Frequency

object Frequency {

  private case class Hz(h: Double) extends Frequency
  private case class Khz(h: Double) extends Frequency

  implicit val frequencyIsCssValue = new CssValue[Frequency] {
    def cssRepr(self: Frequency) = self match {
      case Hz(h) => s"${h}Hz"
      case Khz(h) => s"${h}kHz"
    }
  }

  def hz(h: Double) = Hz(h)
  def khz(h: Double) = Khz(h)

}
