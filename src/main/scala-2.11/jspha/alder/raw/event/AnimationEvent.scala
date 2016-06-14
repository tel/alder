package jspha.alder.raw.event

import org.scalajs.dom
import scala.scalajs.js

/**
  * Created by tel on 5/31/16.
  */
@js.native
trait AnimationEvent extends Event {
  type NativeEvent = dom.AnimationEvent

  val animationName: String
  val pseudoElement: String
  val elapsedTime: Float
}
