package jspha.alder.vdom

import scala.language.implicitConversions

import jspha.alder.raw.{Element, Props, React}

import scala.scalajs.js

/**
  * A TagMod, or Tag Modifier, represents an attribute value, a style, a
  * child, or a React key definition for a given (simple) Virtual DOM node.
  */
trait TagMod extends (TagMod.Args => TagMod.Args) {
  def buildElement(tag: String): Element = apply(TagMod.Args.zero) toElement tag

  /**
    * Enable a TagMod only conditionally.
    */
  def when(cond: Boolean): TagMod =
    if (cond)
      this
    else
      None
}

object TagMod {

  def apply(f: Args => Args): TagMod = new TagMod {
    def apply(a: Args) = f(a)
  }

  def zero: TagMod = TagMod(identity[Args])

  def append(f1: TagMod, f2: => TagMod): TagMod = TagMod(f1 andThen f2)

  implicit def optionalTagMod(option: Option[TagMod]): TagMod =
    option.fold(zero)(identity _)

  case class Args(props: Map[String, js.Any],
                  classes: Set[String],
                  children: js.Array[Element],
                  key: Option[String],
                  styles: Map[String, String]) {

    def toElement(tag: String): Element = {

      import js.JSConverters._

      val propsWithStyles: Map[String, js.Any] =
        if (styles.isEmpty) props
        else {
          val forgottenStyles: js.Any = styles.toJSDictionary
          props + ("styles" -> forgottenStyles)
        }

      val propsWithKey: Map[String, js.Any] =
        key match {
          case None => propsWithStyles
          case Some(k) => propsWithStyles + ("key" -> k)
        }

      val propsWithClassName: Map[String, js.Any] =
        propsWithKey + ("className" -> classes.mkString(" "))

      React.createElement(
        tag,
        Props.ofMap(propsWithClassName),
        children:_*
      )
    }

    def addProp(key: String, value: js.Any) = copy(props = props + (key -> value))
    def addClass(name: String) = copy(classes = classes + name)
    def setKey(keyName: String) = copy(key = Some(keyName))
    def addChild(child: Element) = copy(children = children :+ child)
    def addStyle(key: String, value: String) = copy(styles = styles + (key -> value))

    // NOTE: It's vital that we combine a sequence of children into a single
    // element and then add it to the sequence. Without this, React won't
    // recognize that a *set* of children have been added all at once and
    // then won't fire a missing keys warning when necessary. Since this is
    // actually the source of some terrifically nefarious bugs it's important
    // we don't clobber that behavior through flattening.
    def addChildren(childs: Seq[Element]) =
      copy(children = children :+ Element.ofSequence(childs))
  }

  object Args {
    val zero: Args = Args(
      props = Map(),
      classes = Set(),
      children = js.Array(),
      key = None,
      styles = Map()
    )
  }

}
