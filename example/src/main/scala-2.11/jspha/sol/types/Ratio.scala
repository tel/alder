package jspha.sol.types

import jspha.sol.internal.CssValue

case class Ratio(numerator: Int, denominator: Int)

object Ratio {
  def ratioIsCssValue: CssValue[Ratio] = new CssValue[Ratio] {
    def cssRepr(self: Ratio) = s"${self.numerator}/${self.denominator}"
  }
}
