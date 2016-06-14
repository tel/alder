package jspha.alder

import jspha.alder.raw.{Element, Singl}

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.{ThisFunction0, ThisFunction1, ThisFunction2, UndefOr}

/**
  * More Scala-like interface for creating new React components. Can be a
  * little inefficient. Definitely worth avoiding unless you need to work in
  * a sophisticated manner with React lifecycle callbacks.
  */
trait Spec[P, S] {

  /**
    * Name to be displayed in debugging messages and the React dev tools.
    */
  val displayName: Option[String]

  /**
    * The render() method is required.
    *
    * When called, it should examine props and state and return a
    * single child element. This child element can be either a virtual
    * representation of a native DOM component (such as <div /> or
    * React.DOM.div()) or another composite component that you've defined
    * yourself.
    *
    * The render() function should be pure, meaning that it does not modify
    * component state, it returns the same result each time it's invoked, and it
    * does not read from or write to the DOM or otherwise interact with the
    * browser (e.g., by using setTimeout). If you need to interact with the
    * browser, perform your work in componentDidMount() or the other lifecycle
    * methods instead. Keeping render() pure makes server rendering more
    * practical and makes components easier to think about.
    */
  def render(comp: Component[P, S]): Element

  /**
    * Invoked once before the component is mounted. The return value will be
    * used as the initial value of `this.state`.
    */
  def getInitialState: Option[S] = None

  /**
    * Invoked once and cached when the class is created. Values in the
    * mapping will be set on this.props if that prop is not specified by the
    * parent component (i.e. using an in check).
    *
    * This method is invoked before any instances are created and thus
    * cannot rely on this.props. In addition, be aware that any complex
    * objects returned by getDefaultProps() will be shared across instances,
    * not copied.
    */
  def getDefaultProps: Option[P] = None

  /**
    * Invoked once, both on the client and server, immediately before the initial
    * rendering occurs. If you call setState within this method, render() will see the
    * updated state and will be executed only once despite the state change.
    */
  def componentWillMount(component: Component[P, S]): Unit = ()

  /**
    * Invoked once, only on the client (not on the server), immediately after
    * the initial rendering occurs. At this point in the lifecycle, you can
    * access any refs to your children (e.g., to access the underlying DOM
    * representation). The componentDidMount() method of child components is
    * invoked before that of parent components. If you want to integrate with
    * other JavaScript frameworks, set timers using setTimeout or
    * setInterval, or send AJAX requests, perform those operations in this method.
    */
  def componentDidMount(comp: Component[P, S]): Unit = ()

  /**
    * Invoked when a component is receiving new props. This method is not
    * called for the initial render. Use this as an opportunity to react to a prop
    * transition before render() is called by updating the state using
    * this.setState(). The old props can be accessed via this.props. Calling
    * this.setState() within this function will not trigger an additional render.
    */
  def componentWillReceiveProps(comp: Component[P, S], newProps: P): Unit = ()

  /**
    * Invoked before rendering when new props or state are being received.
    * This method is not called for the initial render or when forceUpdate is used.
    * Use this as an opportunity to return false when you're certain that the
    * transition to the new props and state will not require a component update.
    *
    * If shouldComponentUpdate returns false, then render() will be
    * completely skipped until the next state change. In addition,
    * componentWillUpdate and componentDidUpdate will not be called.
    *
    * By default, shouldComponentUpdate always returns true to prevent subtle
    * bugs when state is mutated in place, but if you are careful to always treat
    * state as immutable and to read only from props and state in render() then you
    * can override shouldComponentUpdate with an implementation that compares the old
    * props and state to their replacements. If performance is a bottleneck,
    * especially with dozens or hundreds of components, use shouldComponentUpdate to
    * speed up your app.
    */
  def shouldComponentUpdate(comp: Component[P, S], newProps: P, newState: S): Boolean =
    true

  /**
    * Invoked immediately before rendering when new props or state are being received.
    * This method is not called for the initial render. Use this as an
    * opportunity to perform preparation before an update occurs.
    *
    * You cannot use this.setState() in this method. If you need to update state in
    * response to a prop change, use componentWillReceiveProps instead.
    */
  def componentWillUpdate(comp: Component[P, S], newProps: P, newState: S): Unit = ()

  /**
    * Invoked immediately after the component's updates are flushed to the
    * DOM. This method is not called for the initial render. Use this as an
    * opportunity to operate on the DOM when the component has been updated.
    */
  def componentDidUpdate(comp: Component[P, S], newProps: P, newState: S): Unit = ()

  /**
    * Invoked immediately before a component is unmounted from the DOM.
    * Perform any necessary cleanup in this method, such as invalidating
    * timers or cleaning up any DOM elements that were created in componentDidMount.
    */
  def componentWillUnmount(comp: Component[P, S]): Unit = ()

  private val self = this

  /**
    * Any Spec is ultimately capable of behaving as a React ComponentClass
    * for the creation of new Elements.
    */
  lazy val componentClass: raw.ComponentClass[P, S] =
    raw.React.createClass(rawSpec)

  /**
    * Underlying this Spec is a "raw" representation which can be directly
    * passed to React functions.
    */
  lazy val rawSpec: raw.Spec[P, S] = new raw.Spec[P, S] {
    import js.JSConverters._

    val displayName: UndefOr[String] = self.displayName.orUndefined

    val render: ThisFunction0[raw.Component[P, S], Element] =
      (c: raw.Component[P, S]) => self.render(Component.fromRaw(c))

    override val componentWillMount: UndefOr[ThisFunction0[raw.Component[P, S], Unit]] =
      raw.Spec.value { (c: raw.Component[P, S]) =>
        self.componentWillMount(Component.fromRaw(c))
      }

    override val componentDidMount: UndefOr[ThisFunction0[raw.Component[P, S], Unit]] =
      raw.Spec.value { (c: raw.Component[P, S]) =>
        self.componentDidMount(Component.fromRaw(c))
      }

    override val componentWillReceiveProps: UndefOr[ThisFunction1[raw.Component[P, S], Singl[P], Unit]] =
      raw.Spec.value { (c: raw.Component[P, S], props: Singl[P]) =>
        self.componentWillReceiveProps(Component.fromRaw(c), props.value)
      }

    override val shouldComponentUpdate: UndefOr[ThisFunction2[raw.Component[P, S], Singl[P], Singl[S], Boolean]] =
      raw.Spec.value { (c: raw.Component[P, S], props: Singl[P], state: Singl[S]) =>
        self.shouldComponentUpdate(Component.fromRaw(c), props.value, state.value)
      }

    override val componentWillUpdate: UndefOr[ThisFunction2[raw.Component[P, S], Singl[P], Singl[S], Unit]] =
      raw.Spec.value { (c: raw.Component[P, S], props: Singl[P], state: Singl[S]) =>
        self.componentWillUpdate(Component.fromRaw(c), props.value, state.value)
      }

    override val componentDidUpdate: UndefOr[ThisFunction2[raw.Component[P, S], Singl[P], Singl[S], Unit]] =
      raw.Spec.value { (c: raw.Component[P, S], props: Singl[P], state: Singl[S]) =>
        self.componentDidUpdate(Component.fromRaw(c), props.value, state.value)
      }

    override val componentWillUnmount: UndefOr[ThisFunction0[raw.Component[P, S], Unit]] =
      raw.Spec.value { (c: raw.Component[P, S]) =>
        self.componentDidMount(Component.fromRaw(c))
      }
  }

}

object Spec {
  implicit def specIsRaw[P, S](spec: Spec[P, S]): raw.Spec[P, S] =
    spec.rawSpec

  implicit def specIsClass[P, S](spec: Spec[P, S]): raw.ComponentClass[P, S] =
    spec.componentClass
}
