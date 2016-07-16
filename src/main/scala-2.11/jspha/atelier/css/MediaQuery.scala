package jspha.atelier.css

/**
  * Media queries are propositions against the current medium.
  */
sealed trait MediaQuery

object MediaQuery {

  case class OfMediaType(mt: MediaType) extends MediaQuery

  implicit object PrintMediaQuery extends Print[MediaQuery] {
    def doc(a: MediaQuery) = a match {
      case OfMediaType(mt) => rec(mt)
    }
  }

}
