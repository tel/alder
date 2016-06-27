package jspha.alder.example.todomvc.cs

import jspha.alder.facet.Static

object Footer extends Static {
  import Dsl._
  import Html._

  def view =
    footer(
      className("footer"),
      TodoCounter(),
      FilterBank(),
      ClearCompletedButton()
    )
}
