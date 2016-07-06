package jspha.atelier.css

/**
  * A namespace for a HTML tag.
  */
sealed trait Namespace

object Namespace {

  case object Any extends Namespace

  case class Named(ns: String) extends Namespace

  implicit object PrintNamespace extends Print[Namespace] {
    def print(a: Namespace) = a match {
      case Any => "*"
      case Named(s) => s
    }
  }

}
