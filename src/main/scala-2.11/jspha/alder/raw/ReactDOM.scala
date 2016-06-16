package jspha.alder.raw

import scala.scalajs.js
import org.scalajs.dom

/**
  * Basic pieces of the ReactDOM Api for rendering React Elements to browser
  * DOM nodes. This same interface is exposed through `hifiui.Component` and
  * it is preferable to use that one.
  */
@js.native
object ReactDOM extends js.Object {
  def render(rel: Element, el: dom.Element): Unit =
    js.native

  def render(rel: Element, el: dom.Element, onRender: () => Unit): Unit =
    js.native

  def unmountComponentAtNode(el: dom.Element): Unit =
    js.native
}
