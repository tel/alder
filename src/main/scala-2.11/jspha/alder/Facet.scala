package jspha.alder

import jspha.alder.raw._
import org.scalajs.dom

import scala.scalajs.js

/**
  * An App is a simplifying abstraction over the entire React component
  * apparatus. Instead of constructing components directly, define
  * collections of Apps which manage state purely with localized updates and
  * callbacks.
  *
  * Apps are an implementation of the "Elm Architecture".
  */
abstract class Facet {

  /**
    * Provided as a value when extending the Facet class so that you can
    * easily `import Dsl._` without having to find the proper import.
    */
  val Dsl = vdom.Dsl

  /**
    * The displayName string is used in debugging messages and the React
    * debugger.
    */
  val displayName = this.getClass.getSimpleName

  /**
    * The Model for an App describes the entire (pure, immutable) state for
    * which the application is responsible for and rendered from. Models must
    * be provided a notion of equality so that we understand when to re-render.
    */
  type Model

  /**
    * The Action of an App is a concrete representation of state transitions
    * which the model might undergo. Or, on the flip side, it's a
    * representation of changes which might be *requested* by the running
    * application.
    */
  type Action

  /**
    * Defines an equivalence relation on the Model space.
    */
  def modelEqual(m1: Model, m2: Model): Boolean = m1 == m2

  /**
    * An App's Model state undergoes transitions according to the Actions it
    * receives. The `step` function provides these state machine semantics.
    */
  def step(action: Action, model: Model): Model

  /**
    * Finally, an App has a *view* described as a Virtual DOM tree which is
    * produced over the data in the Model. The App is provided both the
    * current Model value and a "submit" callback for requesting Actions.
    *
    * The view method is protected since you almost always want to go through
    * `apply` instead. If you really want to use the view there is `_unsafeView`
    */
  def view(model: Model, submit: Action => Unit): Element

  /**
    * Direct access to the view function. Rarely useful.
    */
  def _unsafeView(model: Model, submit: Action => Unit): Element =
    view(model, submit)

  /**
    * Render's an App given both a model value and a callback for submitting
    * actions.
    */
  def apply(model: Model, submit: Action => Unit): Element =
    React.createElement(thisComponent, raw.Singl(AppProps(model, submit)))

  def withKey(key: String)(model: Model, submit: Action => Unit): Element =
    React.createElement(
      thisComponent,
      raw.Singl.withKey(key)(AppProps(model, submit))
    )



  private case class AppProps(model: Model, submit: Action => Unit)

  private type RS = raw.Spec[AppProps, Unit]
  private type RC = raw.Component[AppProps, Unit]

  private val self = this

  // This is SO UGLY, but the point is that it's hidden behind layers.
  private lazy val thisSpec: RS =
    new raw.Spec[AppProps, Unit] {
      val displayName: js.UndefOr[String] = self.displayName

      val render: js.ThisFunction0[RC, Element] =
        (comp: RC) => comp.props.get.value match {
          case AppProps(model, submit) => view(model, submit)
        }

      override val shouldComponentUpdate: js.UndefOr[js.ThisFunction2[RC, Singl[AppProps], Singl[Unit], Boolean]] =
        raw.Spec.value {
          (comp: RC, sprops: Singl[AppProps], sstate: Singl[Unit]) => {
            val oldProps = comp.props.get.value
            val newProps = sprops.value
            if (oldProps eq newProps)
              false
            else
              !modelEqual(oldProps.model, newProps.model)
          }
        }
    }

  private lazy val thisComponent: ComponentClass[AppProps, Unit] =
    React.createClass(thisSpec)
}

object Facet {
  type Aux[M, A] = Facet {
    type Model = M
    type Action = A
  }

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
}