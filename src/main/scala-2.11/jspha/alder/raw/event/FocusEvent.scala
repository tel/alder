package jspha.alder.raw.event

import org.scalajs.dom
import scala.scalajs.js

/**
  * Created by tel on 5/31/16.
  */
@js.native
trait FocusEvent extends Event {
  type NativeEvent = dom.FocusEvent
  val relatedTarget: dom.EventTarget
}
