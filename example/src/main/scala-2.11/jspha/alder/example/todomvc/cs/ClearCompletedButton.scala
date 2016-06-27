package jspha.alder.example.todomvc.cs

import jspha.alder.facet.Static

object ClearCompletedButton extends Static {
  import Dsl._
  import Html._

  def view =
    button(
      className("clear-completed"),
      "Clear completed"
    )
}
