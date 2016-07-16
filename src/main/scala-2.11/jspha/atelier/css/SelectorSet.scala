package jspha.atelier.css

import scala.language.implicitConversions

case class SelectorSet(head: Selector, tail: Seq[Selector]) {
  def :+(selector: Selector): SelectorSet =
    copy(tail = tail :+ selector)

  def +:(selector: Selector): SelectorSet =
    copy(head = selector, tail = head +: tail)
}

object SelectorSet {

  implicit def atom(a: SelectorAtom): SelectorSet = singleton(a)
  implicit def singleton(a: Selector): SelectorSet = SelectorSet(a, Seq())

  implicit object PrintSelectorSet extends Print[SelectorSet] {
    import jspha.atelier.internal.Doc
    import Doc._

    def doc(a: SelectorSet): Doc = {
      val docs: Seq[Doc] = (a.head +: a.tail) map rec[Selector]
      intercalate(", ", docs)
    }
  }

}
