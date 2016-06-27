package jspha.alder.example.todomvc.cs

import jspha.alder.facet.Static

object TodoCounter extends Static {
  import Dsl._
  import Html._

  def view =
    span(
      className("todo-count"),
      strong("0"), " items left"
    )
}
