package jspha.sol.dsl

import jspha.sol.dsl.types.{Length, Ratio, Resolution}
import jspha.sol.internal.{Builder, NonEmptyList}

/**
  * A MediaQuery is composed of the conjunction of atomic queries asserting
  * either a `MediaType` or a feature constraint
  */
sealed trait MediaQuery {

  /**
    * Conjoin two queries forming the query which only holds when they both do.
    */
  def &(other: MediaQuery) =
    this match {
      case MediaQuery.Conj(mqs) =>
        other match {
          case MediaQuery.Conj(mqsOther) =>
            MediaQuery.Conj(mqs ++ mqsOther)
          case _ => MediaQuery.Conj(mqs :+ other)
        }
      case _ =>
        other match {
          case MediaQuery.Conj(mqsOther) =>
            MediaQuery.Conj(this +: mqsOther)
          case _ => MediaQuery.Conj(Seq(this, other))
        }
    }

  /**
    * Negate a `MediaQuery` all together. Since `MediaQuery`s ultimately end
    * up in a kind of disjunctive normal form, you can only negate them once.
    */
  def not: MediaQueryDisj =
    MediaQueryDisj.Fails(this)

  def isTrue: Boolean =
    MediaQuery.isTrue(this)
}

object MediaQuery {

  implicit final case class Type(mt: MediaType) extends MediaQuery
  case class Feature(key: String, value: Option[String]) extends MediaQuery
  final case class Conj(mqs: NonEmptyList[MediaQuery]) extends MediaQuery

  val isCssFragment: CssFragment[MediaQuery] = new CssFragment[MediaQuery] {
    def write(self: MediaQuery): Builder =
      self match {
        case Type(mt) => CssValue.of(mt)
        case Feature(k, mayv) => mayv match {
          case None => k
          case Some(v) => s"($k: $v)"
        }
        case Conj(nel) =>
          Builder.ofSeq[MediaQuery](
            f = CssFragment.write(_),
            bs = nel.seq,
            sep = " and "
          )
      }
  }

  /**
    * Listing of standard features.
    */
  object Feature {

    def of(name: String): MediaQuery =
      Feature(name, None)

    def of[A: CssValue](name: String, value: A): MediaQuery =
      Feature(name, Some(CssValue.of(value)))

    def literal(name: String, value: String): MediaQuery =
      Feature(name, Some(value))

    /**
      * Indicates the number of bits per color component of the output device.  If the device is not a color device, this value is zero.
      */
    object color extends Feature("color", None) {
      def apply(value: Int) = of("color", value)
      def min(value: Int): MediaQuery = of("min-color", value)
      def max(value: Int): MediaQuery = of("max-color", value)
    }

    /**
      * Indicates the number of entries in the color look-up table for the output device.
      */
    object colorIndex extends Feature("color-index", None) {
      def apply(value: Int) = of("color-index", value)
      def min(value: Int): MediaQuery = of("min-color-index", value)
      def max(value: Int): MediaQuery = of("max-color-index", value)
    }

    /**
      * Describes the aspect ratio of the targeted display area of the output device.  This value consists of two positive integers separated by a slash ("/") character.  This represents the ratio of horizontal pixels (first term) to vertical pixels (second term).
      */
    object aspectRatio extends Feature("aspect-ratio", None) {
      def apply(value: Ratio) = of("aspect-ratio", value)
      def min(value: Ratio): MediaQuery = of("min-aspect-ratio", value)
      def max(value: Ratio): MediaQuery = of("max-aspect-ratio", value)
    }

    /**
      * Determines whether the output device is a grid device or a bitmap device.  If the device is grid-based (such as a TTY terminal or a phone display with only one font), the value is 1.  Otherwise it is zero.
      */
    object grid extends Feature("grid", None) {
      def apply(bool: Boolean) = bool match {
        case true => of("grid", 1)
        case false => of("grid", 0)
      }
    }

    /**
      * The height media feature describes the height of the output device's rendering surface (such as the height of the viewport or of the page box on a printer).
      */
    object height extends Feature("height", None) {
      def apply(value: Length) = of("height", value)
      def min(value: Length): MediaQuery = of("min-height", value)
      def max(value: Length): MediaQuery = of("max-height", value)
    }

    /**
      * Indicates the number of bits per pixel on a monochrome (greyscale) device.  If the device isn't monochrome, the device's value is 0.
      */
    object monochrome extends Feature("monochrome", None) {
      def apply(value: Int) = of("monochrome", value)
      def min(value: Int): MediaQuery = of("min-monochrome", value)
      def max(value: Int): MediaQuery = of("max-monochrome", value)
    }

    /**
      * Indicates whether the viewport is in landscape (the display is wider than it is tall) or portrait (the display is taller than it is wide) mode.
      */
    object orientation extends Feature("orientation", None) {
      val landscape: MediaQuery = literal("orientation", "landscape")
      val portrait: MediaQuery = literal("orientation", "portrait")
    }

    /**
      * Indicates the resolution (pixel density) of the output device.  The resolution may be specified in either dots per inch (dpi) or dots per centimeter (dpcm).
      */
    object resolution extends Feature("resolution", None) {
      def apply(value: Resolution) = of("resolution", value)
      def min(value: Resolution): MediaQuery = of("min-resolution", value)
      def max(value: Resolution): MediaQuery = of("max-resolution", value)
    }

    /**
      * Describes the scanning process of television output devices.
      */
    object scan extends Feature("scan", None) {
      val progressive: MediaQuery = literal("scan", "progressive")
      val interlace: MediaQuery = literal("scan", "interlace")
    }

    /**
      * The width media feature describes the width of the rendering surface of the output device (such as the width of the document window, or the width of the page box on a printer).
      */
    object width extends Feature("width", None) {
      def apply(value: Length) = of("width", value)
      def min(value: Length): MediaQuery = of("min-width", value)
      def max(value: Length): MediaQuery = of("max-width", value)
    }

  }
}
