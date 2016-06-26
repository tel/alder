package jspha.sol.dsl

import scala.util.Random

/**
  * A `Name` represents a generated class name at a particular point in a
  * Registry hierarchy.
  */
case class Name[T <: Name.Type](localName: String,
                                prefixes: Seq[String],
                                nonce: String) {

  /**
    * Provides a unique string representing this `Name`. Used as the actual
    * identifier which shows up in class lists or CSS files.
    */
  lazy val mkString: String =
    (prefixes :+ localName :+ nonce).mkString("-")

}

object Name {

  sealed trait Type
  case class Class() extends Type
  case class Animation() extends Type
  case class Font() extends Type
  case class CounterStyle() extends Type

  /**
    * Generate a fresh name given a local identifier and an optional stack
    * of prefixes. Each call to `fresh` produces a unique name unequal to any
    * others even if they share localNames and prefixes.
    */
  def fresh[A](localName: String, prefixes: Seq[String] = Seq()): Name[A] =
    Name[A](
      localName,
      prefixes,
      nonce = Random.alphanumeric.take(4).mkString
    )

  implicit val counterStyleIsCssValue: CssValue[Name[CounterStyle]] =
    new CssValue[Name[CounterStyle]] {
      def cssRepr(self: Name[CounterStyle]) = self.localName
    }

}
