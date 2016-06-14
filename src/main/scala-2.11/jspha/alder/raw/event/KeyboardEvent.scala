package jspha.alder.raw.event

import org.scalajs.dom
import scala.scalajs.js

/**
  * Created by tel on 5/31/16.
  */
@js.native
trait KeyboardEvent extends Event {
  type NativeEvent = dom.KeyboardEvent

  def getModifierState(key: String): Boolean
  val altKey: Boolean
  val ctrlKey: Boolean
  val metaKey: Boolean
  val shiftKey: Boolean
  val repeat: Boolean

  val charCode: Int
  val key: String
  val keyCode: Int
  val locale: String
  val location: Int
  val which: Int

}
