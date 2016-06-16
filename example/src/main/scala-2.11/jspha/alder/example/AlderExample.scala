package jspha.alder.example

import jspha.alder.{Facet, Renderer}
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport
object AlderExample extends JSApp {

  val App =
    Facet.Initialized(cs.CounterSet)(cs.CounterSet.init())

  val renderer =
    new Renderer(App, dom.document.getElementById("app")) {
      override def onAction(action: Action, newState: Model) =
        println("---> " + action)
    }

  @JSExport
  def main() =
    renderer.start()

  @JSExport
  def printState(): Unit =
    println(renderer.getState)

}
