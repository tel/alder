package jspha.alder.example.todomvc.cs

import jspha.alder.facet.Quiet

object FilterSelector extends Quiet {
  import Dsl._
  import Html._

  case class Model(name: String, route: String, selected: Boolean = false)

  def view(model: Model) =
    a(
      className("selected").when(model.selected),
      Attrs.href(model.route),
      model.name
    )

}
