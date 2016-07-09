package jspha.atelier.css

import jspha.atelier.internal.Document

/**
  * A namespace for a HTML tag.
  */
sealed trait Namespace

object Namespace {

  case object Any extends Namespace

  case class Named(ns: String) extends Namespace

  implicit object PrintNamespace extends Print[Namespace] {
    def doc(a: Namespace) = a match {
      case Any => Document.text("*")
      case Named(s) => Document.text(s)
    }
  }

}
