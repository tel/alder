package jspha.alder.example.todomvc.cs

import jspha.alder.Facet

// These are here just to show the structure of the list items
// List items should get the class `editing` when editing and `completed`
// when marked as completed.
object TodoItem extends Facet {
  import Dsl._
  import Html._

  case class Model(completed: Boolean,
                   editing: Boolean,
                   text: String)

  def init(text: String): Model =
    Model(completed = false, editing = false, text = text)

  sealed trait Action
  case object Toggle extends Action
  case object Destroy extends Action
  case object BeginEditing extends Action
  case object EndEditing extends Action

  def step(action: Action, model: Model) =
    action match {
      case Toggle => model.copy(completed = !model.completed)
      case Destroy => model
      case BeginEditing => model.copy(editing = true)
      case EndEditing => model.copy(editing = false)
    }

  def view(model: Model, submit: Action => Unit) =
    li(
      className("completed").when(model.completed),
      div(
        className("view"),
        input(
          className("toggle"),
          Attrs.`type`("checkbox"),
          Attrs.checked(true).when(model.completed),
          Handlers.onClick(_ => submit(Toggle))
        ),
        label(model.text),
        button(
          className("Destroy"),
          Handlers.onClick(_ => submit(Destroy))
        ),
        Handlers.onDoubleClick(_ => submit(BeginEditing))
      ),
      input(
        className("edit"),
        Attrs.value(model.text)
      )
    )
}
