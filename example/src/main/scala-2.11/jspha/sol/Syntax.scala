package jspha.sol

import jspha.sol.properties._

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

  trait Properties
    extends ModOps[Mod]
      with Mixin[Mod]
      with FlexCluster[Mod]
      with BorderCluster[Mod]


  val Properties: Properties

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
