package jspha.alder.raw.event

import org.scalajs.dom
import scala.scalajs.js

/**
  * Created by tel on 5/31/16.
  */
@js.native
trait CompositionEvent extends Event {
  type NativeEvent = dom.CompositionEvent
  val data: String
}
