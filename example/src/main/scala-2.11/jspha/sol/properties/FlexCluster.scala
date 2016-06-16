package jspha.sol.properties

trait FlexCluster[Mod] { this : Mixin[Mod] =>

  /**
    * The flex CSS property is a shorthand property specifying the ability of a flex item to alter its dimensions to fill available space. Flex items can be stretched to use available space proportional to their flex grow factor or their flex shrink factor to prevent overflow.
    * https://developer.mozilla.org/en-US/docs/Web/CSS/flex
    */
  object flex {

    // TODO: shorthand
    // TODO: flex-flow shorthand (https://developer.mozilla.org/en-US/docs/Web/CSS/flex-flow)

    /**
      * The flex-basis CSS property specifies the flex basis which is the initial main size of a flex item. This property determines the size of the content-box unless specified otherwise using box-sizing.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/flex-basis
      */
    object basis
      extends Property[Int]("flex-basis")
        with AutoValues
        with StandardGlobalValues {

      val fill = assignModLiterally(this, "fill")
      val content = assignModLiterally(this, "content")
      val maxContent = assignModLiterally(this, "max-content")
      val minContent = assignModLiterally(this, "min-content")
      val fitContent = assignModLiterally(this, "fit-content")

    }

    /**
      * The flex-direction CSS property specifies how flex items are placed in the flex container defining the main axis and the direction (normal or reversed).
      * Note that the value row and row-reverse are affected by the directionality of the flex container. If its dir attribute is ltr, row represents the horizontal axis oriented from the left to the right, and row-reverse from the right to the left; if the dir attribute is rtl, row represents the axis oriented from the right to the left, and row-reverse from the left to the right.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/flex-direction
      */
    object direction
      extends Property[Nothing]("flex-direction")
        with StandardGlobalValues {

      /**
        * The flex container's main-axis is defined to be the same as the text direction. The main-start and main-end points are the same as the content direction.
        */
      val row = assignModLiterally(this, "row")

      /**
        * Behaves the same as row but the main-start and main-end points are permuted.
        */
      val rowReverse = assignModLiterally(this, "row-reverse")

      /**
        * The flex container's main-axis is the same as the block-axis. The main-start and main-end points are the same as the before and after points of the writing-mode.
        */
      val column = assignModLiterally(this, "column")

      /**
        * Behaves the same as column but the main-start and main-end are permuted.
        */
      val columnReverse = assignModLiterally(this, "column-reverse")

    }

    /**
      * The CSS flex-wrap property specifies whether flex items are forced into a single line or can be wrapped onto multiple lines. If wrapping is allowed, this property also enables you to control the direction in which lines are stacked.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/flex-wrap
      */
    object wrap
      extends Property[Nothing]("flex-wrap")
        with StandardGlobalValues {

      /**
        * The flex items are laid out in a single line which may cause the flex container to overflow. The cross-start is either equivalent to start or before depending flex-direction value.
        */
      val nowrap = assignModLiterally(this, "nowrap")

      /**
        * The flex items break into multiple lines. The cross-start is either equivalent to start or before depending flex-direction value and the cross-end is the opposite of the specified cross-start.
        */
      val wrap = assignModLiterally(this, "wrap")

      /**
        * Behaves the same as wrap but cross-start and cross-end are permuted.
        */
      val wrapReverse = assignModLiterally(this, "wrap-reverse")
    }

    /**
      * The flex-grow CSS property specifies the flex grow factor of a flex item. It specifies what amount of space inside the flex container the item should take up.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/flex-grow
      */
    object grow
      extends Property[Double]("flex-grow")
        with StandardGlobalValues

    /**
      * The flex-shrink CSS property specifies the flex shrink factor of a flex item.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/flex-shrink
      */
    object shrink
      extends Property[Double]("flex-shrink")
        with StandardGlobalValues

  }

  object align {

    /**
      * The CSS align-content property aligns a flex container's lines within the flex container when there is extra space on the cross-axis.
      * This property has no effect on single line flexible boxes.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/align-content
      */
    object content
      extends Property[Nothing]("align-content")
        with StandardGlobalValues {

      /**
        * Lines are packed starting from the cross-start. Cross-start edge of the first line and cross-start edge of the flex container are flushed together. Each following line is flush with the preceding.
        */
      val flexStart = assignModLiterally(this, "flex-start")

      /**
        * Lines are packed starting from the cross-end. Cross-end of the last line and cross-end of the flex container are flushed together. Each preceding line is flushed with the following line.
        */
      val flexEnd = assignModLiterally(this, "flex-end")

      /**
        * Lines are packed toward the center of the flex container. The lines are flushed with each other and aligned in the center of the flex container. Space between the cross-start edge of the flex container and first line and between cross-end of the flex container and the last line is the same.
        */
      val center = assignModLiterally(this, "center")

      /**
        * Lines are evenly distributed in the flex container. The spacing is done such as the space between two adjacent items is the same. Cross-start edge and cross-end edge of the flex container are flushed with respectively first and last line edges.
        */
      val spaceBetween = assignModLiterally(this, "space-between")

      /**
        * Lines are evenly distributed so that the space between two adjacent lines is the same. The empty space before the first and after the last lines equals half of the space between two adjacent lines.
        */
      val spaceAround = assignModLiterally(this, "space-around")

      /**
        * Lines stretch to use the remaining space. The free-space is split equally between all the lines.
        */
      val stretch = assignModLiterally(this, "stretch")
    }

    /**
      * The CSS align-items property aligns flex items of the current flex line the same way as justify-content but in the perpendicular direction.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/align-items
      */
    object items
      extends Property[Nothing]("align-items")
        with StandardGlobalValues {

      /**
        * The cross-start margin edge of the flex item is flushed with the cross-start edge of the line.
        */
      val flexStart = assignModLiterally(this, "flex-start")

      /**
        * The cross-end margin edge of the flex item is flushed with the cross-end edge of the line.
        */
      val flexEnd = assignModLiterally(this, "flex-end")

      /**
        * The flex item's margin box is centered within the line on the cross-axis. If the cross-size of the item is larger than the flex container, it will overflow equally in both directions.
        */
      val center = assignModLiterally(this, "center")

      /**
        * All flex items are aligned such that their baselines align. The item with the largest distance between its cross-start margin edge and its baseline is flushed with the cross-start edge of the line.
        */
      val baseline = assignModLiterally(this, "baseline")

      /**
        * Flex items are stretched such as the cross-size of the item's margin box is the same as the line while respecting width and height constraints.
        */
      val stretch = assignModLiterally(this, "stretch")
    }

    /**
      * The align-self CSS property aligns flex items of the current flex line overriding the align-items value. If any of the flex item's cross-axis margin is set to auto, then align-self is ignored.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/align-self
      */
    object self
      extends Property[Nothing]("align-self")
        with AutoValues
        with StandardGlobalValues {

      /**
        * The cross-start margin edge of the flex item is flushed with the cross-start edge of the line.
        */
      val flexStart = assignModLiterally(this, "flex-start")

      /**
        * The cross-end margin edge of the flex item is flushed with the cross-end edge of the line.
        */
      val flexEnd = assignModLiterally(this, "flex-end")

      /**
        * The flex item's margin box is centered within the line on the cross-axis. If the cross-size of the item is larger than the flex container, it will overflow equally in both directions.
        */
      val center = assignModLiterally(this, "center")

      /**
        * All flex items are aligned such that their baselines align. The item with the largest distance between its cross-start margin edge and its baseline is flushed with the cross-start edge of the line.
        */
      val baseline = assignModLiterally(this, "baseline")

      /**
        * Flex items are stretched such as the cross-size of the item's margin box is the same as the line while respecting width and height constraints.
        */
      val stretch = assignModLiterally(this, "stretch")
    }
  }

  /**
    * The CSS justify-content property defines how the browser distributes space between and around flex items along the main-axis of their container.
    * The alignment is done after the lengths and auto margins are applied, meaning that, if there is at least one flexible element, with flex-grow different from 0, it will have no effect as there won't be any available space.
    * https://developer.mozilla.org/en-US/docs/Web/CSS/justify-content
    */
  object justifyContent
    extends Property[Nothing]("justify-content")
      with StandardGlobalValues {

    /**
      * The flex items are packed starting from the main-start. Margins of the first flex item is flushed with the main-start edge of the line and each following flex item is flushed with the preceding.
      */
    val flexStart = assignModLiterally(this, "flex-start")

    /**
      * The flex items are packed starting from the main-end. The margin edge of the last flex item is flushed with the main-end edge of the line and each preceding flex item is flushed with the following.
      */
    val flexEnd = assignModLiterally(this, "flex-end")

    /**
      * The flex items are packed toward the center of the line. The flex items are flushed with each other and aligned in the center of the line. Space between the main-start edge of the line and first item and between main-end and the last item of the line is the same.
      */
    val center = assignModLiterally(this, "center")

    /**
      * Flex items are evenly distributed along the line. The spacing is done such as the space between two adjacent items is the same. Main-start edge and main-end edge are flushed with respectively first and last flex item edges.
      */
    val spaceBetween = assignModLiterally(this, "space-between")

    /**
      * Flex items are evenly distributed so that the space between two adjacent items is the same. The empty space before the first and after the last items equals half of the space between two adjacent items.
      */
    val spaceAround = assignModLiterally(this, "space-around")
  }

}
