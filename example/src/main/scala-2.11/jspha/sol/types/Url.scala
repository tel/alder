package jspha.sol.types

import scala.language.implicitConversions
import jspha.sol.internal.CssValue

case class Url(url: String)

object Url {

  val urlIsCssValue: CssValue[Url] = ???

  implicit def ofString(name: String): Url = apply(name)

}
