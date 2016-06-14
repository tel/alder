package jspha.alder.raw.event

import org.scalajs.dom
import scala.scalajs.js

/**
  * Created by tel on 5/31/16.
  */
@js.native
trait TouchEvent extends Event {
  type NativeEvent = dom.TouchEvent

  def getModifierState(key: String): Boolean
  val altKey: Boolean
  val ctrlKey: Boolean
  val metaKey: Boolean
  val shiftKey: Boolean

  val touches: dom.TouchList
  val targetTouches: dom.TouchList
  val changedTouches: dom.TouchList
}
