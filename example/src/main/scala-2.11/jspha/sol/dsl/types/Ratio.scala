package jspha.sol.dsl.types

import jspha.sol.dsl.CssValue

case class Ratio(numerator: Int, denominator: Int)

object Ratio {
  implicit val ratioIsCssValue: CssValue[Ratio] = new CssValue[Ratio] {
    def cssRepr(self: Ratio) = s"${self.numerator}/${self.denominator}"
  }
}
