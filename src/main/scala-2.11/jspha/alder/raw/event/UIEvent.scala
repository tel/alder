package jspha.alder.raw.event

import org.scalajs.dom
import scala.scalajs.js

/**
  * Created by tel on 5/31/16.
  */
@js.native
trait UIEvent extends Event {
  type NativeEvent = dom.UIEvent

  val detail: Int

  // AbstractView not implemented in org.scalajs.dom!
  // val view: dom.AbstractView
}
