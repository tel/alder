package jspha.alder.example.cs

import jspha.alder.Factor
import jspha.alder.raw.Element
import jspha.alder.vdom.Dsl

import scala.util.Random

object CounterSet extends Factor {
  val Sub = CounterWithControls

  case class Model(memory: Map[String, Sub.Model], order: Seq[String])

  sealed trait Action
  case class Down(target: String, action: Sub.Action) extends Action
  case object New extends Action

  def makeName(): String =
    Random.alphanumeric.take(10).mkString

  def downAt(target: String) = (sa: Sub.Action) => Down(target, sa)

  def step(action: Action, model: Model): Model = action match {
    case Down(target, Sub.Delete) =>
      model.copy(
        memory = model.memory - target,
        order = model.order.filterNot(_ == target)
      )
    case Down(target, subaction) =>
      model.copy(
        memory =
          model.memory + (target -> Sub.step(subaction, model.memory(target)))
      )

    case New => {
      val newName = makeName()
      model.copy(
        memory = model.memory + (newName -> Sub.init()),
        order = model.order :+ newName
      )
    }
  }

  protected def view(model: Model, submit: (Action) => Unit): Element = {

    import Dsl._
    import Html._

    val subfs =
      model.order.map { name =>
        Sub.withKey(name)(model.memory(name), downAt(name) andThen submit)
      }

    div(
      button("New", Handlers.onClick { _ => submit(New) }),
      subfs
    )
  }

  def init(counters: Seq[Sub.Model] = Seq()): Model =
    counters.foldLeft(Model(memory = Map.empty, order = Seq())) {
      case (m, st) =>
        val newName = makeName()
        m.copy(
          memory = m.memory + (newName -> st),
          order = m.order :+ newName
        )
    }
}
