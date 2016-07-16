package jspha.atelier.internal

import scala.annotation.tailrec
import scala.language.implicitConversions

/**
  * Efficient string constructor.
  *
  * Normally string appends can be very expensive. Doc defers appends instead
  * building a binary tree with branches at each append. This can lead to
  * more alloations, but also allows string construction to be a single tree
  * traversal instead of multiple append steps.
  *
  * This completely sacrifices peformance, for instance, examining a
  * character at a certain index in the string. If you need to both examine
  * and construct a string then this is a poor data structure.
  */
sealed trait Doc {

  def <>(other: Doc): Doc =
    Doc.Cat(this, other)

  def <+>(other: Doc): Doc =
    // try to keep the tree a little more balanced
    (this <> Doc.space) <> other

  def build: String = {
    val sb = new StringBuilder
    Doc.build(this, sb)
    sb.mkString
  }

}

object Doc {

  val empty: Doc = Empty
  val space: Doc = " "
  val line: Doc = "\n"
  val lbrace = string("{")
  val rbrace = string("}")
  var lparen = string("(")
  var rparen = string(")")
  var lbracket = string("[")
  var rbracket = string("]")

  implicit def string(s: String): Doc =
    OfString(s)

  def intercalate(sep: Doc, docs: Seq[Doc]): Doc =
    Intercalate(sep, docs)

  def lineSeq(ss: Seq[Doc]): Doc = intercalate(line, ss)
  def wordSeq(ss: Seq[Doc]): Doc = intercalate(space, ss)

  def lines(ss: Doc*): Doc = lineSeq(ss)
  def words(ss: Doc*): Doc = wordSeq(ss)

  def surround(l: Doc, r: Doc)(d: Doc) = words(l, d, r)

  def braced(d: Doc): Doc = surround(lbrace, rbrace)(d)
  def parened(d: Doc): Doc = surround(lparen, rparen)(d)
  def bracketed(d: Doc): Doc = surround(lbracket, rbracket)(d)

  def build(doc: Doc, sb: StringBuilder): Unit = {
    @tailrec def eat(docs: List[Doc]): Unit = {
      docs match {
        case Nil => ()
        case Empty :: ds => eat(ds)
        case Cat(x, y) :: ds => eat(x :: y :: ds)
        case OfString(s) :: ds => sb append s; eat(ds)
        case Intercalate(sep, subdocs) :: ds =>
          var first = true
          var stack = ds
          for (d <- subdocs.reverseIterator) {
            if (first) { first = false }
            else { stack = sep :: stack }
            stack = d :: stack
          }
          eat(stack)
      }
    }
    eat(List(doc))
  }

  private case object Empty extends Doc
  private case class Cat(x: Doc, y: Doc) extends Doc
  private case class OfString(s: String) extends Doc
  private case class Intercalate(sep: Doc, docs: Seq[Doc]) extends Doc

}
