package jspha.alder.example

import jspha.alder.raw.{Element, ReactDOM}
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport
object AlderExample extends JSApp {

  val TheFactor = cs.CounterSet

  var state = TheFactor.init()

  def submit(act: TheFactor.Action): Unit = {
    println(act)
    state = TheFactor.step(act, state)
    render()
  }

  def render() = {
    val el: Element = TheFactor(state, submit)
    ReactDOM.render(el, target)
  }

  val target = dom.document.querySelector("#app")

  @JSExport
  def main(): Unit = {
    render()
  }

}
