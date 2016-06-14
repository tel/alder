package jspha.alder.raw.event

import org.scalajs.dom
import scala.scalajs.js

/**
  * Created by tel on 5/31/16.
  */
@js.native
trait WheelEvent extends Event {
  type NativeEvent = dom.WheelEvent

  val deltaMode: Int
  val deltaX: Int
  val deltaY: Int
  val deltaZ: Int
}
