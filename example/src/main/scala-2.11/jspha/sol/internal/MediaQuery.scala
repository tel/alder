package jspha.sol.internal

import jspha.sol.dsl.CssValue
import jspha.sol.dsl.types.{Length, Ratio, Resolution}

sealed trait MediaConstraint {
  def repr: String
}

sealed trait MediaQuery extends MediaConstraint {
  import MediaConstraint._

//  def & (other: MediaQuery): MediaQuery = And(this, other)
}

object MediaConstraint {

  def apply(mqs: MediaQuery*): String =
    mqs.map(_.repr).mkString(", ")

  case class Not(mq: MediaQuery) extends MediaConstraint {
    def repr = s"not ${mq.repr}"
  }

  case object All extends MediaQuery { def repr = "all" }
  case object Screen extends MediaQuery { def repr = "screen" }
  case object Print extends MediaQuery { def repr = "print" }
  case object Speech extends MediaQuery { def repr = "speech" }

  case class Feature(attr: String, value: Option[String]) extends MediaQuery {
    def repr = value match {
      case None => s"($attr)"
      case Some(repr) => s"($attr: $repr)"
    }
  }

  object Feature {

    def feature[A: CssValue](name: String, value: A): MediaQuery =
      Feature(name, Some(CssValue.of(value)))

    def featureLiteral(name: String, value: String): MediaQuery =
      Feature(name, Some(value))

    /**
      * Indicates the number of bits per color component of the output device.  If the device is not a color device, this value is zero.
      */
    object color extends Feature("color", None) {
      def apply(value: Int) = feature("color", value)
      def min(value: Int): MediaQuery = feature("min-color", value)
      def max(value: Int): MediaQuery = feature("max-color", value)
    }

    /**
      * Indicates the number of entries in the color look-up table for the output device.
      */
    object colorIndex extends Feature("color-index", None) {
      def apply(value: Int) = feature("color-index", value)
      def min(value: Int): MediaQuery = feature("min-color-index", value)
      def max(value: Int): MediaQuery = feature("max-color-index", value)
    }

    /**
      * Describes the aspect ratio of the targeted display area of the output device.  This value consists of two positive integers separated by a slash ("/") character.  This represents the ratio of horizontal pixels (first term) to vertical pixels (second term).
      */
    object aspectRatio extends Feature("aspect-ratio", None) {
      def apply(value: Ratio) = feature("aspect-ratio", value)
      def min(value: Ratio): MediaQuery = feature("min-aspect-ratio", value)
      def max(value: Ratio): MediaQuery = feature("max-aspect-ratio", value)
    }

    /**
      * Determines whether the output device is a grid device or a bitmap device.  If the device is grid-based (such as a TTY terminal or a phone display with only one font), the value is 1.  Otherwise it is zero.
      */
    object grid extends Feature("grid", None) {
      def apply(bool: Boolean) = bool match {
        case true => feature("grid", 1)
        case false => feature("grid", 0)
      }
    }

    /**
      * The height media feature describes the height of the output device's rendering surface (such as the height of the viewport or of the page box on a printer).
      */
    object height extends Feature("height", None) {
      def apply(value: Length) = feature("height", value)
      def min(value: Length): MediaQuery = feature("min-height", value)
      def max(value: Length): MediaQuery = feature("max-height", value)
    }

    /**
      * Indicates the number of bits per pixel on a monochrome (greyscale) device.  If the device isn't monochrome, the device's value is 0.
      */
    object monochrome extends Feature("monochrome", None) {
      def apply(value: Int) = feature("monochrome", value)
      def min(value: Int): MediaQuery = feature("min-monochrome", value)
      def max(value: Int): MediaQuery = feature("max-monochrome", value)
    }

    /**
      * Indicates whether the viewport is in landscape (the display is wider than it is tall) or portrait (the display is taller than it is wide) mode.
      */
    object orientation extends Feature("orientation", None) {
      val landscape: MediaQuery = featureLiteral("orientation", "landscape")
      val portrait: MediaQuery = featureLiteral("orientation", "portrait")
    }

    /**
      * Indicates the resolution (pixel density) of the output device.  The resolution may be specified in either dots per inch (dpi) or dots per centimeter (dpcm).
      */
    object resolution extends Feature("resolution", None) {
      def apply(value: Resolution) = feature("resolution", value)
      def min(value: Resolution): MediaQuery = feature("min-resolution", value)
      def max(value: Resolution): MediaQuery = feature("max-resolution", value)
    }

    /**
      * Describes the scanning process of television output devices.
      */
    object scan extends Feature("scan", None) {
      val progressive: MediaQuery = featureLiteral("scan", "progressive")
      val interlace: MediaQuery = featureLiteral("scan", "interlace")
    }

    /**
      * The width media feature describes the width of the rendering surface of the output device (such as the width of the document window, or the width of the page box on a printer).
      */
    object width extends Feature("width", None) {
      def apply(value: Length) = feature("width", value)
      def min(value: Length): MediaQuery = feature("min-width", value)
      def max(value: Length): MediaQuery = feature("max-width", value)
    }


  }

//  case class And(left: MediaQuery, right: MediaQuery) extends MediaQuery

}
