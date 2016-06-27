package jspha.alder.example.todomvc.cs

import jspha.alder.facet.Initialized

object TodoItemStar extends Initialized {

  case class Model(completed: Boolean,
                   editing: Boolean,
                   text: String)

  def init =
    Model(
      completed = false,
      editing = false,
      text = "Test todo please ignore"
    )

  sealed trait Action
  case object Toggle extends Action
  case object BeginEditing extends Action
  case object Destroy extends Action
  case class EndEditing(newText: String) extends Action

  def step(action: Action, model: Model) =
    action match {
      case Toggle => model.copy(completed = ! model.completed)
      case BeginEditing => model.copy(editing = true)
      case EndEditing(newText) => model.copy(
        editing = false,
        text = newText
      )
      case _ => model
    }

  def view(model: Model, submit: Action => Unit) = {
    import Dsl._
    import Html._

    li(
      className("editing").when(model.editing),
      className("checked").when(model.completed),
      div(
        className("view"),
        input(
          className("toggle"),
          Attrs.`type`("checkbox"),
          Attrs.defaultChecked(model.completed),
          Handlers.onClick { _ => submit(Toggle) }
        ),
        label(
          Handlers.onDoubleClick { _ => submit(BeginEditing) },
          model.text
        ),
        button(
          className("destroy"),
          Handlers.onClick { _ => submit(Destroy) }
        )
      )
    )
  }


}
