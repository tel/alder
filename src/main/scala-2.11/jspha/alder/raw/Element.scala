package jspha.alder.raw

import scala.language.implicitConversions
import scala.scalajs.js

/**
  * Elements form the backbone of your React Virtual DOM tree structure. They
  * can be generated from strings (text nodes), from Html or Svg dom
  * factories (simple nodes) or from React ComponentClasses (sophisticated
  * nodes).
  */
@js.native
trait Element extends js.Object {}

object Element {

  import js.JSConverters._

  implicit def optionElementIsElement(opt: Option[Element]): Element =
    opt match {
      case None => None.orUndefined.asInstanceOf[Element]
      case Some(el) => el
    }

  implicit def ofSequence(seq: Seq[Element]): Element =
    seq.toJSArray.asInstanceOf[Element]
}
