package jspha.sol.dsl.types

import jspha.sol.dsl.CssValue

import scala.language.implicitConversions

case class Url(url: String)

object Url {

  val urlIsCssValue: CssValue[Url] = ???

  implicit def ofString(name: String): Url = apply(name)

}
