package jspha.alder.raw

import scala.language.implicitConversions
import scala.scalajs.js

/**
  * The "raw" interface to React's simple Html and Svg DOM factories. It's
  * unlikely you will ever use this but instead it's necessary for giving
  * good types to the React interface. Instead, consider using TagMod and the
  * DOM node factories in the `vdom` package.
  */
@js.native
trait Props extends js.Object {}

object Props {
  import js.JSConverters._

  implicit def ofMap(mp: Map[String, js.Any]): Props =
    mp.toJSDictionary.asInstanceOf[Props]
}
