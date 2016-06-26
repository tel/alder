package jspha.sol.dsl

/**
  * `MediaType`s are the various recognized types of medium that a webpage
  * may be viewed upon.
  */
sealed trait MediaType

object MediaType {
  case object All extends MediaType
  case object Aural extends MediaType
  case object Braille extends MediaType
  case object Handheld extends MediaType
  case object Print extends MediaType
  case object Projection extends MediaType
  case object Screen extends MediaType
  case object Tty extends MediaType
  case object Tv extends MediaType
  case object Embossed extends MediaType

  implicit val isCssValue: CssValue[MediaType] =
    new CssValue[MediaType] {
      def cssRepr(self: MediaType) = self match {
        case All => "all"
        case Aural => "aural"
        case Braille => "braille"
        case Handheld => "handheld"
        case Print => "print"
        case Projection => "projection"
        case Screen => "screen"
        case Tty => "tty"
        case Tv => "tv"
        case Embossed => "embossed"
      }
    }
}

