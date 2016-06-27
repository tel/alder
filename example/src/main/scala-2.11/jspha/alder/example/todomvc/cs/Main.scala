package jspha.alder.example.todomvc.cs

import jspha.alder.facet.Static

// This section should be hidden by default and shown when there are todos
object Main extends Static {
  import Dsl._
  import Html._

  def view =
    section(
      className("main"),
      input(className("toggle-all"), Attrs.`type`("checkbox")),
      label(Attrs.htmlFor("toggle-all"), "Mark all as complete"),
      TodoList()
    )
}
