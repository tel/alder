package jspha.alder.example.todomvc

import jspha.alder.Renderer
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

@JSExport
object TodoMVC extends JSApp {

  val App = cs.TodoApp

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
