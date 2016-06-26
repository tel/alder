package jspha.sol.internal

case class Name(prefixes: Seq[String], name: String, nonce: String) {

  lazy val className: String =
    (prefixes :+ name :+ nonce).mkString("-")

}
