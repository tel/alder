package jspha.sol.dsl.propertySet

import jspha.sol.dsl.CssValue
import jspha.sol.internal.{Mod, Property}
import jspha.sol.dsl.types.{Color, Length}

trait Border { this: CommonProperties =>

  /**
    * The border CSS property is a shorthand property for setting the individual border property values in a single place in the style sheet. border can be used to set the values for one or more of: border-width, border-style, border-color.
    * Like all shorthand properties, a missing value for one of the longhand properties is set to the corresponding initial value. Also note that border-image, though not settable using this shorthand, is reset to its initial value, that is, none. This allows to use border to reset any border settings set earlier in the cascade. As the W3C intends to preserve this property in future level of the spec, it is recommended to use this method to reset border settings.
    * https://developer.mozilla.org/en-US/docs/Web/CSS/border
    */
  object border
    extends MixinTraits.BorderCore
      with MixinTraits.BorderCommon {

    def apply(builders: (this.type => Mod)*): Mod = builders.map(_(this))

    protected val prefix = "border"

    /**
      * The border-collapse CSS property determines whether a table's borders are separated or collapsed. In the separated model, adjacent cells each have their own distinct borders. In the collapsed model, adjacent table cells share borders.
      * The separated model is the traditional HTML table border model. Adjacent cells each have their own distinct borders. The distance between them given by the border-spacing property.
      * In the collapsed border model, adjacent table cells share borders. In that model, the border-style value of inset behaves like groove, and outset behaves like ridge.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/border-collapse
      */
    object collapse
      extends Property("border-collapse")
        with InheritGlobalValue {

      /**
        * Is a keyword requesting the use of the separated-border table rendering model. It is the default value.
        */
      val separate = this ::= "separate"

      /**
        * Is a keyword requesting the use of the collapsed-border table rendering model.
        */
      val collapse = this ::= "collapse"
    }

    /**
      * The border-spacing CSS property specifies the distance between the borders of adjacent table cells (only for the separated borders model). This is equivalent to the cellspacing attribute in presentational HTML, but an optional second value can be used to set different horizontal and vertical spacing.
      * The border-spacing value is also used along the outside edge of the table, where the distance between the table's border and the cells in the first/last column or row is the sum of the relevant (horizontal or vertical) border-spacing and the relevant (top, right, bottom, or left) padding on the table.
      * This property applies only when border-collapse is separate.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/border-spacing
      */
    object spacing
      extends Property("border-spacing")
        with InheritGlobalValue
        with MixinTraits.BorderLengthOrEllipsoid

    /**
      * The border-top CSS property is a shorthand that sets the values of border-top-color, border-top-style, and border-top-width. These properties describe the top border of elements.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/border-top
      */
    object top
      extends MixinTraits.BorderCore
        with MixinTraits.BorderCommon
        with MixinTraits.BorderTopBottomCommon {
      protected val prefix = "border-top"
      def apply(builders: (this.type => Mod)*): Mod = builders.map(_(this))
    }

    /**
      * The border-bottom CSS property is a shorthand that sets the values of border-bottom-color, border-bottom-style, and border-bottom-width. These properties describe the bottom border of elements.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/border-bottom
      */
    object bottom
      extends MixinTraits.BorderCore
        with MixinTraits.BorderCommon
        with MixinTraits.BorderTopBottomCommon {
      protected val prefix = "border-bottom"
      def apply(builders: (this.type => Mod)*): Mod = builders.map(_(this))
    }

    /**
      * The border-left CSS property is a shorthand that sets the values of border-left-color, border-left-style, and border-left-width. These properties describe the left border of elements.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/border-left
      */
    object left
      extends MixinTraits.BorderCore
        with MixinTraits.BorderCommon {
      protected val prefix = "border-left"
      def apply(builders: (this.type => Mod)*): Mod = builders.map(_(this))
    }

    /**
      * The border-right CSS property is a shorthand that sets the values of border-right-color, border-right-style, and border-right-width. These properties describe the right border of elements.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/border-right
      */
    object right
      extends MixinTraits.BorderCore
        with MixinTraits.BorderCommon {
      protected val prefix = "border-right"
      def apply(builders: (this.type => Mod)*): Mod = builders.map(_(this))
    }

  }

  object MixinTraits {

    trait BorderCore {
      protected val prefix: String
    }

    trait BorderCommon { self: BorderCore =>
      /**
        * The border-top-color CSS property sets the color of the top border of an element. Note that in many cases the shorthand CSS properties border-color or border-top are more convenient and preferable.
        * https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-color
        */
      object color
        extends Property(prefix + "-color")
          with InheritGlobalValue {
        def apply(col: Color) = this := col
      }


      /**
        * The border-top-style CSS property sets the line style of the top border of a box.
        * https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-style
        */
      object style
        extends Property("border-top-style")
          with InheritGlobalValue {

        /**
          * Like for the hidden keyword, displays no border. In that case, except if a background image is set, the calculated values of border-top-width will be 0, even if specified otherwise through the property. In case of table cell and border collapsing, the none value has the lowest priority: it means that if any other conflicting border is set, it will be displayed.
          */
        val none = this ::= "none"

        /**
          * Like for the none keyword, displays no border. In that case, except if a background image is set, the calculated values of border-top-width will be 0, even if specified otherwise through the property. In case of table cell and border collapsing, the hidden value has the highest priority: it means that if any other conflicting border is set, it won't be displayed.
          */
        val hidden = this ::= "hidden"

        /**
          * Displays a series of rounded dots. The spacing of the dots are not defined by the specification and are implementation-specific. The radius of the dots is half the calculated border-top-width.
          */
        val dotted = this ::= "dotted"

        /**
          * Displays a series of short square-ended dashes or line segments. The exact size and length of the segments are not defined by the specification and are implementation-specific.
          */
        val dashed = this ::= "dashed"

        /**
          * Displays a single, straight, solid line.
          */
        val solid = this ::= "solid"

        /**
          * Displays two straight lines that add up to the pixel amount defined as border-width or border-top-width.
          */
        val double = this ::= "double"

        /**
          * Displays a border leading to a carved effect. It is the opposite of ridge.
          */
        val groove = this ::= "groove"

        /**
          * Displays a border with a 3D effect, like if it is coming out of the page. It is the opposite of groove.
          */
        val ridge = this ::= "ridge"

        /**
          * Displays a border that makes the box appear embedded. It is the opposite of outset. When applied to a table cell with border-collapse set to collapsed, this value behaves like groove.
          */
        val inset = this ::= "inset"

        /**
          * Displays a border that makes the box appear in 3D, embossed. It is the opposite of inset. When applied to a table cell with border-collapse set to collapsed, this value behaves like ridge.
          */
        val outset = this ::= "outset"
      }

      /**
        * The border-top-width CSS property sets the width of the top border of a box.
        * Is either a non-negative explicit <length> value or a keyword denoting the thickness of the bottom border.
        * The specification doesn't precisely define the thickness of each of the keywords, which is therefore implementation specific. Nevertheless, it requests that the thickness does follow the thin ≤ medium ≤ thick inequality and that the values are constant on a single document.
        * https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-width
        */
      object width
        extends Property("border-top-width")
          with InheritGlobalValue {

        def apply(len: Length) = this := len

        val thin = this ::= "thin"
        val medium = this ::= "medium"
        val thick = this ::= "thick"
      }
    }

    trait BorderLengthOrEllipsoid { this : Property =>
      def apply(radius: Length): Mod =
        this ::= CssValue.of(radius)

      def apply(horizontal: Length, vertical: Length): Mod =
        this ::= CssValue.CssList.of(horizontal, vertical)
    }

    trait BorderTopBottomCommon { self: BorderCore =>
      /**
        * The border-top-left-radius CSS property sets the rounding of the top-left corner of the element. The rounding can be a circle or an ellipse, or if one of the value is 0,no rounding is done and the corner is square.
        * A background, being an image or a color, is clipped at the border, even a rounded one; the exact location of the clipping is defined by the value of the background-clip property.
        * https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-left-radius
        */
      object leftRadius
        extends Property("border-top-left-radius")
          with InheritGlobalValue
          with BorderLengthOrEllipsoid

      /**
        * The border-top-right-radius CSS property sets the rounding of the top-right corner of the element. The rounding can be a circle or an ellipse, or if one of the value is 0 no rounding is done and the corner is square.
        * A background, being an image or a color, is clipped at the border, even a rounded one; the exact location of the clipping is defined by the value of the background-clip property.
        * https://developer.mozilla.org/en-US/docs/Web/CSS/border-top-right-radius
        */
      object rightRadius
        extends Property("border-top-right-radius")
          with InheritGlobalValue
          with BorderLengthOrEllipsoid
    }

  }

}

