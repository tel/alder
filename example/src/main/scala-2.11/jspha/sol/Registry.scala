package jspha.sol

class Registry private(private val prefixNames: Seq[String]) {

  /**
    * Constructs a fresh, base registry.
    */
  def this() = this(Seq())

  /**
    * Extend this to form a Sub-registry. Classes registered in that module
    * will appear in the base registry but also have their names qualified
    * by the path from the base registry all the way down to the Sub chain.
    */
  class Sub(subName: String)
    extends Registry(prefixNames :+ subName)

//  protected def module(name: String)(mods: Mod*): Style = {
//    val nonce = Random.alphanumeric.take(4).mkString
//    val realName = Name(mode, prefixNames, name, nonce)
//    registerClass(realName, mods)
//    Style(mods, realName)
//  }

//  private var globalRegistry: Map[Selector.Global, Seq[Mod]] = Map()
//  private var classRegistry: Map[Name, Seq[Mod]] = Map()
//
//  private def registerGlobal(selector: Selector.Global, value: Seq[Mod]): Unit =
//    globalRegistry = globalRegistry + (selector -> value)
//
//  private def registerClass(name: Name, value: Seq[Mod]): Unit =
//    classRegistry = classRegistry + (name -> value)

}
