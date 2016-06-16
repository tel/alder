package jspha.alder.example.cs

import jspha.alder.{Facet, Element}

object Counter extends Facet {

  type Model = Int

  sealed trait Action
  case object Inc extends Action
  case object Dec extends Action

  def step(action: Action, model: Int): Int = action match {
    case Inc => model + 1
    case Dec => model - 1
  }

  def view(model: Model, submit: Action => Unit): Element = {

    import Dsl._
    import Html._

    div(
      button("-", Handlers.onClick { _ => submit(Dec) }),
      p(model.toString()),
      button("+", Handlers.onClick { _ => submit(Inc) })
    )
  }

  def init(n: Int = 0): Model = n
}
