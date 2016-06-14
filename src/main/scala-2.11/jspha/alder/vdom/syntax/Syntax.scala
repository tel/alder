package jspha.alder.vdom.syntax

import scala.language.implicitConversions

class Syntax[El, Mod](core: Internal[El, Mod]) extends Core[El, Mod] {
  val Html = new Html(core)
  val Svg = new Svg(core)
  val Handlers = new Handlers(core)

  implicit def textChild(str: String): Mod = core.textChild(str)

  def dangerouslySetInnerHtml(html: String): Mod =
    core.dangerouslySetInnerHtml(html)

  def key(keyName: String): Mod = core.key(keyName)

  implicit def child(el: El): Mod = core.child(el)

  implicit def children(els: Seq[El]): Mod = core.children(els)

  def style(key: String, value: String): Mod = core.style(key, value)

}
