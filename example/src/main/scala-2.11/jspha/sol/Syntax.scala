package jspha.sol

/*

https://css-modules.github.io/webpack-demo/
http://nicolasgallagher.com/about-html-semantics-front-end-architecture/

- namespaces
  - classes (munged)
  - animations "@keyframes" (munged)
  - fonts
- munged names by default: just create whatever names you want
  - munging *should preserve* the names in the module definition
    - let's readers understand the symbology at least a bit
    - postpend a random symbol
    - perhaps not necessary in production (!)
      - just use the random symbol
- XXX global selectors: allows for unmunged-string based selector creation

- nested definition
  - does not create CSS nesting and run afoul of specificity issues
  - instead, creates nested *Scala* names which resolve to (concatenative,
    e.g., "a-b-c") final, munged names
    - like this
      .btn { /* styles */ }
      .uilist { /* styles */ }
      .uilist-item { /* styles */ }
    - not
      .btn { /* styles */ }
      .uilist { /* styles */ }
      .uilist a { /* styles */ }

- style-sets
  - sets of definitions, attribute->value mapping
  - *refinement* selectors
    - e.g.
        &.hover                             // :hover
        &.hover.visited                     // :hover:visited
        &.hover.not(_.visited)              // :hover:not(:visited)
        &.hover.not(_.firstChild.visited)   // :hover:not(:first-child:visited)
        &.nthChild(3).not(".debug")         // :nth-child(3):not(.debug)
        &.nthChild("3n+2")                  // :nth-child(3n+2)
        &.attr("custom-attr", "bla").hover  // [custom-attr="bla"]:hover
        &.attrExists("custom-attr").hover   // [custom-attr]:hover
    - but also e.g.
        @media screen
        @media screen, not handheld
        @media screen and (color), not handheld, (min-height:240px)
    - each attribute->value mapping may exist in the context of a constraint
      - e.g. Map[Constraint, Map[Attribute, Value]]
  - child selectors ("nesting") is disallowed (???)
    - reasons to use it (https://www.sitepoint.com/bem-smacss-advice-from-developers/)
      - "Usually when I’m nesting with BEM is when I want to target an
         element via HTML tag name, or to win a specificity battle when
         components styling overlaps."

- composes: import a set of constraints from another location
  - local constraints override composed constraints
  - multiple compositions override one another?

- autoprefixer---sort of
  - STRETCH GOAL at best
  - we control the AST so we should just emit conformant CSS
    - use https://github.com/bestiejs/platform.js/
  - scalacss on this (+100):
    "When you're generating CSS in the browser (ie. a Scala.JS project)
     you don't need to generate different prefixes because you know what
     rendering engine to target. The Scala.JS version of Defaults provides
     an implicit Env that uses platform.js (embedded) to determine which
     prefixes you need according to caniuse.com data."

- static generation?
  - pros:
    - no runtime (generation) cost
    - more "inspectable" CSS files
  - cons:
    - packaging two files is necessary
    - sophisticated server integration required for build/generation

 */

/**
  * See https://developer.mozilla.org/en-US/docs/Web/CSS/Reference
  */
trait Syntax[Sty, Mod] {

  // CSS types
  /*
    Types
      <angle>
        deg
        grad
        rad
        turn
      <basic-shape>
        circle()
        ellipse()
        inset()
        polygon()
      <blend-mode>
        normal
        multiply
        screen
        overlay
        darken
        lighten
        color-dodge
        color-burn
        hard-light
        soft-light
        difference
        exclusion
        hue
        saturation
        color
        luminosity
      <color>
        hsl()
        hsla()
        rgb()
        rgba()
      <custom-ident>
      <filter-function>
        <blur()> = blur( <length> )
        <brightness()> = brightness( [ <number> | <percentage> ] )
        <contrast()> = contrast( [ <number> | <percentage> ] )
        <drop-shadow()> = drop-shadow( <length>{2,3} <color>? )
        <grayscale()> = grayscale( [ <number> | <percentage> ] )
        <hue-rotate()> = hue-rotate( <angle> )
        <invert()> = invert( [ <number> | <percentage> ] )
        <opacity()> = opacity( [ <number> | <percentage> ] )
        <sepia()> = sepia( [ <number> | <percentage> ] )
        <saturate()> = saturate( [ <number> | <percentage> ] )
      <frequency>
        Hz
        kHz
      <gradient> [subtypes <image>?]
        linear-gradient()
        radial-gradient()
        repeating-linear-gradient()
        repeating-radial-gradient()
      <image>
        image() ???
      <integer>
      <length>
        em
        ex
        ch
        rem
        vh
        vw
        vmin
        vmax
        px
        mm
        q
        cm
        in
        pt
        pc
      <number>
      <percentage>
        n '%'
      <position>
        syntax...
        [ [ left | center | right | top | bottom | <percentage> | <length> ]
          | [ left | center | right | <percentage> | <length> ] [ top | center | bottom | <percentage> | <length> ]
          | [ center | [ left | right ] [ <percentage> | <length> ]? ]
          && [ center | [ top | bottom ] [ <percentage> | <length> ]? ]
        ]
      <ratio>
        n '/' m    no spaces
      <resolution>
        dpcm
        dpi
        dppx
      <shape>
        rect()
      <string>
      <time> (interval)
        s
        ms
      <timing-function>
        cubic-bezier()
        steps()
        linear
        ease
        ease-in
        ease-in-out
        ease-out
        step-start
        step-end
      <transform-function>
        matrix()
        matrix3d()
        perspective()
        rotate()
        rotate3d()
        rotateX()
        rotateY()
        rotateZ()
        scale()
        scale3d()
        scaleX()
        scaleY()
        scaleZ()
        skew()
        skewX()
        skewY()
        translate()
        translate3d()
        translateX()
        translateY()
        translateZ()
      <url>
        url()
      <user-ident>
   */

  case class Property[A](name: String)

  def assignMod[A: CssValue](key: Property[A], value: A): Mod
  def assignModLiterally[A](key: Property[A], value: String): Mod
  def mods(modSeq: Mod*): Mod

  implicit class KeyAssign[A: CssValue](key: Property[A]) {
    def :=(value: A): Mod = assignMod(key, value)
  }

  sealed trait PseudoClass {
    /*
      :active
      :checked
      :default
      :dir(ltr | rtl)
      :disabled
      :empty
      :enabled
      :fullscreen
      :first
      :first-child
      :first-of-type
      :focus
      :hover
      :in-range
      :indeterminate
      :invalid
      :lang()
      :last-child
      :last-of-type
      :left
      :link
      :not()
      :nth-child()
      :nth-last-child()
      :nth-last-of-type()
      :nth-of-type()
      :only-child
      :only-of-type
      :optional
      :out-of-range
      :read-only
      :read-write
      :required
      :right
      :root
      :scope
      :target
      :unresolved
      :valid
      :visited
    */
  }
  sealed trait PseudoElement {
    /*
      ::after
      ::backdrop
      ::before
      ::first-letter
      ::first-line
      ::selection
    */
  }

  // Other top-level declarations
  /*
    @counter-style (see also symbols() for inline counter style defs)
      additive-symbols
      fallback
      negative
      pad
      prefix
      range
      speak-as
      suffix
      symbols
      system

    @font-face
      font-family
      font-feature-settings
      font-stretch
      font-style
      font-variant
      font-weight
      format()
      src
      unicode-range

    @font-feature-values
      annotation()
      @character-variant
      @annotation
      @ornaments
      @styleset
      @stylistic
      @swash

    @keyframes

    @viewport
      height
      max-height
      max-width
      max-zoom
      min-height
      min-width
      min-zoom
      orientation
      user-zoom
      width
      zoom
  */

  // Core Sty builder
  def classNamed(name: String)(mods: Mod*): Sty

  // Sub-selector mods
  def pseudoClass(cls: PseudoClass)(mods: Mod*): Mod
  def pseudoElement(cls: PseudoElement)(mods: Mod*): Mod
  // attribute(attrExpr: AttrExpr)(mods: Mod*): Mod
  // decedent
  // child
  // sibling

  private trait StandardGlobalValues extends Property[_] {
    val inherit: Mod = assignModLiterally(this, "inherit")
    val initial: Mod = assignModLiterally(this, "initial")
    val unset: Mod = assignModLiterally(this, "unset")
  }

  private trait AutoValues extends Property[_] {
    val auto: Mod = assignModLiterally(this, "auto")
  }

  object Properties {

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
  }


  // Other/Unknown
  /*
    attr()
    calc()
    character-variant()
    @charset
    <counter>
      cross-fade()
      element()
      format()
      image-set()
      @import
      local()
      @media
      minmax()
      @namespace
      ornaments()
      @page
      styleset()
      stylistic()
      @supports
      swash()
      var()
  */
}

/*

Properties

 */

