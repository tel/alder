package jspha.alder.raw.event

import org.scalajs.dom
import scala.scalajs.js

/**
  * Created by tel on 5/31/16.
  */
@js.native
trait MouseEvent extends Event {
  type NativeEvent = dom.MouseEvent

  def getModifierState(key: String): Boolean
  val altKey: Boolean
  val ctrlKey: Boolean
  val metaKey: Boolean
  val shiftKey: Boolean

  val relatedTarget: dom.EventTarget

  val button: Int
  val buttons: Int

  val clientX: Int
  val clientY: Int
  val pageX: Int
  val pageY: Int
  val screenX: Int
  val screenY: Int
}
