package jspha.alder.facet

import jspha.alder.Facet

/**
  * An initialized Facet is one which has a fixed (default) value for its
  * model. Many Facets will have rich initialization and cannot be
  * considered Initialized---instead, we need something with a single,
  * preferred default value.
  */
trait Initialized extends Facet {
  def init: Model
}

object Initialized {
  def apply(facet: Facet)(initialState: facet.Model): Initialized = {
    new Initialized {
      type Model = facet.Model
      type Action = facet.Action

      def init = initialState
      def view(model: Model, submit: Action => Unit) =
        facet.view(model, submit)
      def step(action: Action, model: Model) =
        facet.step(action, model)
    }
  }
}

