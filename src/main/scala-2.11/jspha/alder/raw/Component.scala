package jspha.alder.raw

import scala.scalajs.js

/**
  * Components are bundles of state and behavior in your virtual DOM tree.
  * Alder doesn't explicitly use bare React components often, but they are
  * instead usually the value of `this` in Spec callbacks.
  */
@js.native
trait Component[P, S] extends js.Object {

  val props: js.UndefOr[Singl[P]]
  val state: js.UndefOr[Singl[S]]

  def setState(newState: Singl[S]): Unit
  def forceUpdate(): Unit

  // Everything else is deprecated, so we'll just not implement it!

}
