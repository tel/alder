package jspha.sol.properties

/**
  * Created by tel on 6/16/16.
  */
trait Mixin[Mod] extends ModOps[Mod] {

  protected trait InheritGlobalValue extends Property[_] {
    val inherit: Mod = assignModLiterally(this, "inherit")
  }

  protected trait AllGlobalValues
    extends Property[_]
      with InheritGlobalValue {
    val initial: Mod = assignModLiterally(this, "initial")
    val unset: Mod = assignModLiterally(this, "unset")
  }

  protected trait AutoValues extends Property[_] {
    val auto: Mod = assignModLiterally(this, "auto")
  }

}
