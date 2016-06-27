package jspha.alder.vdom

import scala.language.implicitConversions

import jspha.alder.raw.Element
import jspha.alder.vdom.syntax.Internal

import scala.scalajs.js

/**
  * Seed definitions which ultimately provide behavior for the entire Dsl
  * object.
  */
object DslCore extends Internal[Element, TagMod] {

  def makeFactory(tag: String)(mods: TagMod*): Element = {
    val totalMod: TagMod = mods.foldLeft(TagMod.zero) {
      case (a, b) => TagMod.append(a, b)
    }
    totalMod buildElement tag
  }

  def makeCallbackMod[E](name: String)(cb: (E) => Unit): TagMod =
    TagMod(_.addProp(name, cb))

  def makeBooleanValueMod(name: String)(value: Boolean) =
    if (value)
      TagMod(_.addProp(name, name))
    else
      TagMod.zero

  def makeJsValueMod(name: String)(value: js.Any): TagMod =
    TagMod(_.addProp(name, value))

  def dangerouslySetInnerHtml(html: String): TagMod =
    TagMod(_.addProp("dangerouslySetInnerHtml", html))

  def key(keyName: String): TagMod =
    TagMod(_.setKey(keyName))

  def className(name: String): TagMod =
    TagMod(_.addClass(name))

  implicit def child(el: Element): TagMod =
    TagMod(_.addChild(el))

  override implicit def children(els: Seq[Element]): TagMod =
    TagMod(_.addChildren(els))

  implicit def textChild(str: String): TagMod =
    TagMod(_.addChild(str.asInstanceOf[Element]))

  def style(key: String, value: String): TagMod =
    TagMod(_.addStyle(key, value))

}

