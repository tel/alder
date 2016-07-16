package jspha.atelier.css

import jspha.atelier.internal.Doc

/**
  * Values of type `Sheet` represent a single CSS "file"
  */
case class Sheet(blocks: Seq[Decl])

object Sheet {

  implicit object PrintCSS extends Print[Sheet] {
    def doc(css: Sheet) =
      Doc.lines(css.blocks.map(rec[Decl]))
  }

}
