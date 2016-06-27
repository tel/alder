package jspha.alder

import jspha.alder.facet.Initialized
import jspha.alder.raw.ReactDOM
import org.scalajs.dom

/**
  * Embodies a running render loop.
  *
  * TODO: Implement shimmed rAF batched updates
  *
  * - https://gist.github.com/paulirish/1579671
  * - https://github.com/julienetie/request-frame/blob/master/src/request-frame.src.js
  * - https://github.com/petehunt/react-raf-batching/blob/master/requestAnimationFrame.js
  */
class Renderer(val facet: Initialized, target: dom.Element) {

  type Model = facet.Model
  type Action = facet.Action

  private var state =
    facet.init

  /**
    * Callback called each time an action is received and the state updates.
    */
  def onAction(action: Action, newState: Model): Unit =
    ()

  /**
    * View the current application state of the render loop.
    */
  final def getState: Model =
    state

  /**
    * Submit a new action against the application state.
    */
  final def submit(action: Action): Unit = {
    state = facet.step(action, state)
    onAction(action, state)
    requestRender()
  }

  private def requestRender() =
    render()

  private def render() =
    ReactDOM.render(facet(state, submit), target)

  final def start(): Unit =
    render()

  final def dispose(): Unit =
    ReactDOM.unmountComponentAtNode(target)
}

object Renderer {
  def apply(facet: Initialized, target: dom.Element): Renderer = {
    new Renderer(facet, target)
  }

  def apply(facet: Initialized, elementId: String): Renderer = {
    val target = dom.document.getElementById(elementId)
    apply(facet, target)
  }
}

