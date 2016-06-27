package jspha.alder.vdom.syntax

import scala.scalajs.js

trait Internal[El, Mod] extends Core[El, Mod] {
  def makeFactory(s: String)(mods: Mod*): El
  def makeCallbackMod[E](s: String)(cb: E => Unit): Mod
  def makeJsValueMod(s: String)(value: js.Any): Mod
  def makeBooleanValueMod(s: String)(value: Boolean): Mod
}
