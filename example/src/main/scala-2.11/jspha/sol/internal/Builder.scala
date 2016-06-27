package jspha.sol.internal

import scala.language.implicitConversions

/**
  * Functional wrapper around StringBuilder.
  */
case class Builder(f: StringBuilder => StringBuilder)
  extends Function[StringBuilder, StringBuilder] {

  def apply(sb: StringBuilder): StringBuilder = f(sb)

  /**
    * A Builder represents a single string in process of construction;
    * calling write computes its final, static string value. Inefficient if
    * called frequently.
    */
  lazy val write: String = f(new StringBuilder).mkString

  def ++(other: Builder): Builder =
    Builder(f andThen other.f)

}

object Builder {
  implicit def ofString(s: String): Builder =
    Builder(_.append(s))

  val zero: Builder = Builder(identity _)

  def ofSeq(bs: Seq[Builder]): Builder =
    Builder { (sb: StringBuilder) =>
      bs.foreach { (b: Builder) =>
        b(sb)
      }
      sb
    }

  def ofSeq(bs: Seq[Builder], sep: String): Builder =
    ofSeq[Builder](identity _, bs, sep)

  def ofSeq[A](f: A => Builder, bs: Seq[A], sep: String): Builder = {
    val sepBuilder = ofString(sep)
    Builder { (sb: StringBuilder) =>
      var first = true

      for (x <- bs) {
        if (first) {
          f(x)(sb)
          first = false
        } else {
          sepBuilder(sb)
          f(x)(sb)
        }
      }

      sb
    }
  }

}
