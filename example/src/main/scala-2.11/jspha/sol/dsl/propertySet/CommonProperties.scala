package jspha.sol.dsl.propertySet

import jspha.sol.internal.{Mod, Property}

trait CommonProperties {

  protected trait InheritGlobalValue extends Property {
    val inherit: Mod = this ::= "inherit"
  }

  protected trait AllGlobalValues
    extends Property
      with InheritGlobalValue {
    val initial: Mod = this ::= "initial"
    val unset: Mod = this ::= "unset"
  }

  protected trait NoneValues extends Property {
    val none: Mod = this ::= "none"
  }

  protected trait AutoValues extends Property {
    val auto: Mod = this ::= "auto"
  }

}
