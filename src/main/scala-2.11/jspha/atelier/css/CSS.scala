package jspha.atelier.css

import jspha.atelier.internal.Document

/**
  * Values of type `CSS` represent a single CSS "file"
  */
case class CSS(blocks: Seq[Decl])

object CSS {

  implicit object PrintCSS extends Print[CSS] {
    def doc(css: CSS) =
      Document.lines(css.blocks.map(Print[Decl].doc))
  }

}
