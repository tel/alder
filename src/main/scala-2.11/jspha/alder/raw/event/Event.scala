package jspha.alder.raw.event

import org.scalajs.dom
import scala.scalajs.js

@js.native
trait Event extends js.Object {

  type NativeEvent <: dom.Event

  val bubbles: Boolean
  val cancelable: Boolean
  val defaultPrevented: Boolean
  val eventPhase: Int
  val isTrusted: Boolean
  val timeStamp: Int
  val `type`: String

  val currentTarget: dom.EventTarget
  val target: dom.EventTarget

  val nativeEvent: NativeEvent

  def preventDefault(): Unit
  def isDefaultPrevented(): Boolean

  def stopPropagation(): Unit
  def isPropagationStopped(): Boolean

}




























