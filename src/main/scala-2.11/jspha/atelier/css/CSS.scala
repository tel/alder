package jspha.atelier.css

import jspha.atelier.internal.DString

/**
  * Values of type `CSS` represent a single CSS "file"
  */
case class CSS(blocks: Seq[CSS.Block])

object CSS {

  sealed trait Block


  object Block {

    case class Selection(selector: Selector, style: Style) extends Block

    implicit object PrintBlock extends Print[Block] {
      def print(block: Block) = block match {
        case Selection(selector, style) =>
          s"${Print[Selector].print(selector)} { ${Print[Style].print(style)} }"
      }
    }

  }

  implicit object PrintCSS extends Print[CSS] {
    def print(css: CSS) =
      DString.concat(css.blocks.map(Print[Block].print _), " ")
  }

  val example: CSS =
    CSS(Seq(
      Block.Selection(
        Selector.Id("foo"),
        Style(Map(
          "foo" -> "bar"
        ))
      ),
      Block.Selection(
        Selector.Class("foo"),
        Style(Map(
          "foo" -> "bar"
        ))
      ),
      Block.Selection(
        Selector.Element("div", Some(Namespace.Any)),
        Style(Map(
          "foo" -> "bar"
        ))
      )
    ))

}
