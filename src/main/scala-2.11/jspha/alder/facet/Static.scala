package jspha.alder.facet

import jspha.alder.Element

/**
  * A Static Facet is a Quiet facet defined over a trivial Model space
  * (thus becoming automatically Initialized)
  */
trait Static extends Initialized with Quiet {

  type Model = Unit

  def init = ()
  def view(model: Model) = view
  def apply() = view

  /**
    * A Static view, defined once.
    */
  def view: Element

}

