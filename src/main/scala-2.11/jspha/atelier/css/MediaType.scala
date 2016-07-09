package jspha.atelier.css

import jspha.atelier.internal.Document

sealed trait MediaType

object MediaType {

  case object MediaType
  case object All extends MediaType
  case object Screen extends MediaType
  case object Print extends MediaType
  case object Speech extends MediaType

  implicit object PrintMediaType extends Print[MediaType] {

    import Document.text

    def doc(a: MediaType) = a match {
      case All => text("all")
      case Screen => text("screen")
      case Print => text("print")
      case Speech => text("speech")
    }
  }

}
