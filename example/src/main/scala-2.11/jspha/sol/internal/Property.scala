package jspha.sol.internal

import jspha.sol.dsl.CssValue

case class Property(name: String) {
  def :=[A: CssValue](value: A) = Mod.assign(name, CssValue.of(value))
  def ::= (value: String) = Mod.assign(name, value)
}
