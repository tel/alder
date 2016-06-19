package jspha.sol.internal

case class Property[-A: CssValue](name: String) {
  def :=(value: A) = Mod.assign(name, CssValue.of(value))
  def ::= (value: String) = Mod.assign(name, value)
}
