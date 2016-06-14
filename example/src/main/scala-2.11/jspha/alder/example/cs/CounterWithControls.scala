package jspha.alder.example.cs

import jspha.alder.Factor
import jspha.alder.raw.Element
import jspha.alder.vdom.Dsl

object CounterWithControls extends Factor {

  type Model = Counter.Model

  sealed trait Action
  case class Down(action: Counter.Action) extends Action
  case object Delete extends Action

  def step(action: Action, model: Model): Model = action match {
    case Down(act) => Counter.step(act, model)
    case Delete => model
  }

  protected def view(model: Model, submit: Action => Unit): Element = {

    import Dsl._
    import Html._

    div(
      button("delete", Handlers.onClick { _ => submit(Delete) }),
      Counter(model, Down andThen submit)
    )
  }

  def init(n: Int = 0): Model = n
}
