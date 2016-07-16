package jspha.atelier.internal

import scala.language.implicitConversions

/**
  * Efficient string constructor.
  *
  * Normally string appends can be very expensive. Doc defers appends instead
  * building a binary tree with branches at each append. This can lead to
  * more alloations, but also allows string construction to be a single tree
  * traversal instead of multiple append steps.
  */
sealed trait Doc {

  import Doc._

  def apply(sb: StringBuilder): Unit

  def <>(other: Doc): Doc =
    Cat(this, other)

  def <+>(other: Doc): Doc =
    CatSpace(this, other)

  def build: String = {
    val sb = new StringBuilder
    apply(sb)
    sb.mkString
  }
}

object Doc {

  private case object Nil extends Doc {
    def apply(sb: StringBuilder): Unit = ()
  }

  val nil: Doc = Nil

  private case class Cat(x: Doc, y: Doc) extends Doc {
    def apply(sb: StringBuilder): Unit = {
      x(sb)
      y(sb)
    }
  }

  private case class CatSpace(x: Doc, y: Doc) extends Doc {
    def apply(sb: StringBuilder): Unit = {
      x(sb)
      sb append " "
      y(sb)
    }
  }

  private case class OfString(s: String) extends Doc {
    def apply(sb: StringBuilder): Unit =
      sb append s
  }

  private case class Lines(docs: Seq[Doc]) extends Doc {
    def apply(sb: StringBuilder): Unit = {
      var first = true
      for (d <- docs) {
        if (!first) { sb append "\n" }
        d(sb)
        first = false
      }
    }
  }

  private case class Words(docs: Seq[Doc]) extends Doc {
    def apply(sb: StringBuilder): Unit = {
      var first = true
      for (d <- docs) {
        if (!first) { sb append " " }
        d(sb)
        first = false
      }
    }
  }

  implicit def string(s: String): Doc =
    OfString(s)

  def lines(ss: Seq[Doc]): Doc = Lines(ss)
  def words(ss: Seq[Doc]): Doc = Words(ss)

  val lbrace = string("{")
  val rbrace = string("}")

  def braced(d: Doc): Doc = words(Seq(lbrace, d, rbrace))

}
