package jspha.sol.dsl.types

import jspha.sol.dsl.CssValue

trait Resolution

object Resolution {

  case class Dcpm(l: Double) extends Resolution
  case class Dpi(l: Double) extends Resolution
  case class Dppx(l: Double) extends Resolution

  implicit val resolutionIsCssValue = new CssValue[Resolution] {
    def cssRepr(rez: Resolution) = rez match {
      case Dcpm(l) => s"${l}dcpm"
      case Dpi(l) => s"${l}dpi"
      case Dppx(l) => s"${l}dppx"
    }
  }

  def dcpm(self: Double) = Dcpm(self)
  def dpi(self: Double) = Dpi(self)
  def dppx(self: Double) = Dppx(self)
}
