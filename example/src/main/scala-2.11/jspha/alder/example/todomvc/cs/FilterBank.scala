package jspha.alder.example.todomvc.cs

import jspha.alder.facet.Static

object FilterBank extends Static {
  import Dsl._
  import Html._

  def view =
    ul(
      className("filters"),
      li(
        FilterSelector(FilterSelector.Model(
          name = "All",
          route = "#/",
          selected = true
        )),

        FilterSelector(FilterSelector.Model(
          name = "Active",
          route = "#/active"
        )),

        FilterSelector(FilterSelector.Model(
          name = "Completed",
          route = "#/completed"
        ))
      )
    )
}
