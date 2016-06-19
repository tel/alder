package jspha.sol.internal

case class Style(mods: Seq[Mod], name: Name) {

  /**
    * Descend through a stack of Mods and extract a set of top-level CSS
    * declarations.
    */
  def classes: Map[Seq[MediaConstraint], Map[Selector.Local, Seq[Mod]]] =
    ???

}
