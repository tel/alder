package jspha.alder.raw.event

import org.scalajs.dom
import scala.scalajs.js

/**
  * Created by tel on 5/31/16.
  */
@js.native
trait TransitionEvent extends Event {
  type NativeEvent = dom.TransitionEvent

  val propertyName: String
  val pseudoElement: String
  val elapsedTime: Float
}
