package jspha.alder.raw

import scala.scalajs.js

/**
  * Basic pieces of the core React interface. Alder doesn't mock out all of
  * React's interface intentionally as you probably should not be using it
  * directly.
  */
@js.native
object React extends js.Object {

  def createClass[P, S](spec: Spec[P, S]): ComponentClass[P, S] =
    js.native

  def createElement[P, S](comp: ComponentClass[P, S], props: Singl[P]): Element =
    js.native

  def createElement(tag: String, props: Props, children: Element*): Element =
    js.native

  val version: String =
    js.native

}
