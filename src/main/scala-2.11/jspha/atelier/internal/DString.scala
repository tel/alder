package jspha.atelier.internal

import scala.language.implicitConversions

/**
  * Efficient, immutable string building.
  */
case class DString(update: StringBuilder => StringBuilder) extends AnyVal {

  def append(other: DString) =
    DString(update andThen other.update)

  def prepend(other: DString) =
    DString(other.update andThen update)

  def ++(other: DString) =
    append(other)

  def mkString: String =
    update(new StringBuilder()).mkString

  override def toString: String =
    mkString

}

object DString {

  val empty =
    DString(identity)

  implicit def constant(s: String): DString =
    DString { (b: StringBuilder) => b.append(s) }

  def concat(seq: Seq[DString]) =
    seq.foldLeft(empty)(_ ++ _)

  def concat(seq: Seq[DString], sep: String) =
    DString { (sb: StringBuilder) =>
      var first = true
      for (v <- seq) {
        if (first) {
          v.update(sb)
          first = false
        } else {
          sep.update(sb)
          v.update(sb)
        }
      }
      sb
    }

}
