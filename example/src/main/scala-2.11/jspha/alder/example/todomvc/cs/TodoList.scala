package jspha.alder.example.todomvc.cs

import jspha.alder.facet.Static
import jspha.alder.Local

object TodoList extends Static {
  import Dsl._
  import Html._

  def view =
    ul(
      className("todo-list"),
      Local(TodoItem)(
        TodoItem.init("Hello world"),
        println(_)
      )
    )
}
