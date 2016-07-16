package jspha.atelier.css

import jspha.atelier.internal.Doc

sealed trait MediaType

object MediaType {

  case object MediaType
  case object All extends MediaType
  case object Screen extends MediaType
  case object Print extends MediaType
  case object Speech extends MediaType

  implicit object PrintMediaType extends Print[MediaType] {

    import Doc._

    def doc(a: MediaType) = a match {
      case All => "all"
      case Screen => "screen"
      case Print => "print"
      case Speech => "speech"
    }
  }

}
