package jspha.sol.dsl.types

import jspha.sol.dsl.CssValue

case class Ratio(numerator: Int, denominator: Int)

object Ratio {
  def ratioIsCssValue: CssValue[Ratio] = new CssValue[Ratio] {
    def cssRepr(self: Ratio) = s"${self.numerator}/${self.denominator}"
  }
}
