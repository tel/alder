package jspha.alder.example.todomvc.cs

import jspha.alder.facet.Static

object Header extends Static {
  import Dsl._
  import Html._

  def view =
    header(
      className("header"),
      h1("todos"),
      input(
        className("new-todo"),
        Attrs.placeholder("What needs to be done?"),
        Attrs.autoFocus(true)
      )
    )
}
