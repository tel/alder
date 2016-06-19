package jspha.sol.internal

import jspha.sol.Stylesheet._

case class Name(mode: Mode,
                prefixes: Seq[String],
                name: String,
                nonce: String) {

  lazy val className: String =
    mode match {
      case Development => (prefixes :+ name :+ nonce).mkString("-")
      case Production => "_" + nonce
    }

}
