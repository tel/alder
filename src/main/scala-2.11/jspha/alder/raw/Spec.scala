package jspha.alder.raw

import scala.scalajs.js
import scala.scalajs.js.Error
import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * A Spec defines the lifecycle behavior of a React Component. This is the
  * *raw* interface to React specs and can be used to overload essentially
  * whatever behavior you like. Most of the time you should not be accessing
  * this, however!
  */
@ScalaJSDefined
abstract class Spec[P, S] extends js.Object {

  /**
    * Name to be displayed in debugging messages and the React dev tools.
    */
  val displayName: js.UndefOr[String]

  /**
    * The render() method is required.
    *
    * When called, it should examine this.props and this.state and return a
    * single child element. This child element can be either a virtual
    * representation of a native DOM component (such as <div /> or
    * React.DOM.div()) or another composite component that you've defined
    * yourself.
    *
    * You can also return null or false to indicate that you don't want
    * anything rendered. Behind the scenes, React renders a <noscript> tag to
    * work with our current diffing algorithm. When returning null or false,
    * ReactDOM.findDOMNode(this) will return null.
    *
    * The render() function should be pure, meaning that it does not modify
    * component state, it returns the same result each time it's invoked, and it
    * does not read from or write to the DOM or otherwise interact with the
    * browser (e.g., by using setTimeout). If you need to interact with the
    * browser, perform your work in componentDidMount() or the other lifecycle
    * methods instead. Keeping render() pure makes server rendering more
    * practical and makes components easier to think about.
    */
  val render: js.ThisFunction0[Component[P, S], Element]

  /**
    * Invoked once before the component is mounted. The return value will be
    * used as the initial value of `this.state`.
    */
  val getInitialState: js.UndefOr[js.ThisFunction0[Component[P, S], Singl[S]]] =
    Spec.noOp

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
  def getDefaultProps(): js.UndefOr[Singl[P]] = Spec.noOp

  /** The propTypes object allows you to validate props being passed to your
    * components. For more information about propTypes, see Reusable Components.
    *
    * Note, you cannot override this since Alder assumes it controls the
    * basic layer of your props!
    */
  final val propTypes = Spec.defaultPropTypes

  /**
    * Invoked once, both on the client and server, immediately before the initial
    * rendering occurs. If you call setState within this method, render() will see the
    * updated state and will be executed only once despite the state change.
    */
  val componentWillMount: js.UndefOr[js.ThisFunction0[Component[P, S], Unit]] =
    Spec.noOp

  /**
    * Invoked once, only on the client (not on the server), immediately after
    * the initial rendering occurs. At this point in the lifecycle, you can
    * access any refs to your children (e.g., to access the underlying DOM
    * representation). The componentDidMount() method of child components is
    * invoked before that of parent components. If you want to integrate with
    * other JavaScript frameworks, set timers using setTimeout or
    * setInterval, or send AJAX requests, perform those operations in this method.
    */
  val componentDidMount: js.UndefOr[js.ThisFunction0[Component[P, S], Unit]] =
    Spec.noOp

  /**
    * Invoked when a component is receiving new props. This method is not
    * called for the initial render. Use this as an opportunity to react to a prop
    * transition before render() is called by updating the state using
    * this.setState(). The old props can be accessed via this.props. Calling
    * this.setState() within this function will not trigger an additional render.
    */
  val componentWillReceiveProps: js.UndefOr[js.ThisFunction1[Component[P, S], Singl[P], Unit]] =
    Spec.noOp

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
  val shouldComponentUpdate: js.UndefOr[js.ThisFunction2[Component[P, S], Singl[P], Singl[S], Boolean]] =
    Spec.noOp

  /**
    * Invoked immediately before rendering when new props or state are being received.
    * This method is not called for the initial render. Use this as an
    * opportunity to perform preparation before an update occurs.
    *
    * You cannot use this.setState() in this method. If you need to update state in
    * response to a prop change, use componentWillReceiveProps instead.
    */
  val componentWillUpdate: js.UndefOr[js.ThisFunction2[Component[P, S], Singl[P], Singl[S], Unit]] =
    Spec.noOp

  /**
    * Invoked immediately after the component's updates are flushed to the
    * DOM. This method is not called for the initial render. Use this as an
    * opportunity to operate on the DOM when the component has been updated.
    */
  val componentDidUpdate: js.UndefOr[js.ThisFunction2[Component[P, S], Singl[P], Singl[S], Unit]] =
    Spec.noOp

  /**
    * Invoked immediately before a component is unmounted from the DOM.
    * Perform any necessary cleanup in this method, such as invalidating
    * timers or cleaning up any DOM elements that were created in componentDidMount.
    */
  val componentWillUnmount: js.UndefOr[js.ThisFunction0[Component[P, S], Unit]] =
    Spec.noOp
}

object Spec {

  import js.JSConverters._

  def noOp[A]: js.UndefOr[A] = None.orUndefined

  def value[A](a: A): js.UndefOr[A] = Some(a).orUndefined

  protected val defaultPropTypes = new js.Object {
    val value: js.Function3[js.Object, String, String, js.UndefOr[js.Error]] =
      (props: js.Object, propName: String, componentName: String) =>
        if (!props.hasOwnProperty(propName))
          Some(new Error(
            "Alder component " + componentName + " was somehow " +
              "initialized without its value prop."
          )).orUndefined
        else
          None.orUndefined
  }
}
