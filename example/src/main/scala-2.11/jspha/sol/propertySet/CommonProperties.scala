package jspha.sol.propertySet

import jspha.sol.internal.{Mod, Property}

trait CommonProperties {

  protected trait InheritGlobalValue extends Property[Any] {
    val inherit: Mod = this ::= "inherit"
  }

  protected trait AllGlobalValues
    extends Property[Any]
      with InheritGlobalValue {
    val initial: Mod = this ::= "initial"
    val unset: Mod = this ::= "unset"
  }

  protected trait NoneValues extends Property[Any] {
    val none: Mod = this ::= "none"
  }

  protected trait AutoValues extends Property[Any] {
    val auto: Mod = this ::= "auto"
  }

}
