package jspha.alder.vdom.syntax

import scala.scalajs.js

/**
  * Created by tel on 5/31/16.
  */
trait Internal[El, Mod] extends Core[El, Mod] {
  def makeFactory(s: String)(mods: Mod*): El
  def makeCallbackMod[E](s: String)(cb: E => Unit): Mod
  def makeJsValueMod(s: String)(value: js.Any): Mod
}
