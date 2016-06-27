package jspha.alder.example.todomvc.cs

import jspha.alder.facet.Static

object TodoApp extends Static {
  import Dsl._
  import Html._

  def view =
    section(
      className("todoapp"),
      Header(),
      Main(),
      Footer()
    )

}
