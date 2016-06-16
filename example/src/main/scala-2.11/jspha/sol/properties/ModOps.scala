package jspha.sol.properties

import jspha.sol.internal.CssValue

trait ModOps[Mod] {
  def assignMod[A: CssValue](key: Property[A], value: A): Mod
  def assignModLiterally[A](key: Property[A], value: String): Mod
}
