package jspha.alder

import jspha.alder.raw.{ComponentClass, React}

/**
  * A Local component is a React component built from a Facet which uses
  * React component local state to facilitate the Alder mechanics.
  */
class Local[F <: Facet](val facet: F) {

  private val self = this

  case class Props(initialModel: facet.Model,
                   onAction: facet.Action => Unit)

  private object ThisSpec extends Spec[Props, facet.Model] {

    val displayName: Option[String] =
      Some(self.getClass.getSimpleName)

    def getInitialState(props: Props) =
      props.initialModel

    def render(comp: Component[Props, facet.Model]): Element = {
      val model: facet.Model = comp.state.get

      facet(comp.state.get, { (a: facet.Action) =>
        comp.props.get.onAction(a)
        val newModel = facet.step(a, model)
        comp.setState(newModel)
      })
    }
  }

  private lazy val componentClass: ComponentClass[Props, facet.Model] =
    ThisSpec.componentClass

  def apply(initialModel: facet.Model,
            onAction: facet.Action => Unit = _ => ()): Element =
    React.createElement(componentClass, Props(initialModel, onAction))

}

object Local {
  def apply[F <: Facet](facet: F): Local[F] = new Local[F](facet)
}
