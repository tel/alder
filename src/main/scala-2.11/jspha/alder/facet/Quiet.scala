package jspha.alder.facet

import scala.language.implicitConversions
import jspha.alder.{Element, Facet}

/**
  * A Quiet Facet is one which does not emit Actions (in fact, no Actions
  * at all exist). It thus ends up being identical to a (pure) function from
  * Model to virtual Element tree.
  */
trait Quiet extends Facet {

  type Action = Nothing
  def step(action: Action, model: Model) = model
  def view(model: Model, submit: Action => Unit) = view(model)

  /**
    * A single pure function from the Model space to virtual Elements.
    */
  def view(model: Model): Element

  def apply(model: Model) = view(model)
}

object Quiet {

  type Aux[M] = Quiet { type Model = M }

  /**
    * Any function to virtual Elements can be seen as a Quiet Facet.
    */
  implicit def ofFunction[M](f: M => Element): Quiet.Aux[M] =
    new Quiet {
      type Model = M
      def view(model: M) = f(model)
    }
}

