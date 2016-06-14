package jspha.alder.vdom.syntax

import scala.language.implicitConversions

trait Core[El, Mod] {

  implicit def child(el: El): Mod

  implicit def children(els: Seq[El]): Mod

  implicit def textChild(str: String): Mod

  def key(keyName: String): Mod

  def style(key: String, value: String): Mod

  def dangerouslySetInnerHtml(html: String): Mod

}
