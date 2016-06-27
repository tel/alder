package jspha.sol.dsl.types

import jspha.sol.dsl.CssValue

import scala.language.implicitConversions

case class Url(url: String)

object Url {

  implicit val urlIsCssValue: CssValue[Url] =
    new CssValue[Url] {
      def cssRepr(self: Url) = s"url(${self.url})"
    }

  implicit def ofString(name: String): Url = apply(name)

}
