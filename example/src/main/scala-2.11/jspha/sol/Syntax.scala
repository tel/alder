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

trait Syntax[Sty, Mod] {

  case class Property[A](name: String)

  def assignMod[A: CssValue](key: Property[A], value: A): Mod

  implicit class KeyAssign[A: CssValue](key: Property[A]) {
    def :=(value: A): Mod = assignMod(key, value)
  }

  sealed trait PseudoClass
  sealed trait PseudoElement

  // Core Sty builder
  def classNamed(name: String)(mods: Mod*): Sty

  // Sub-selector mods
  def pseudoClass(cls: PseudoClass)(mods: Mod*): Mod
  def pseudoElement(cls: PseudoElement)(mods: Mod*): Mod
  // attribute(attrExpr: AttrExpr)(mods: Mod*): Mod
  // descendent
  // child
  // sibling

  /**
    * See https://developer.mozilla.org/en-US/docs/Web/CSS/Reference
    */
  object Properties {
    object background extends Property[Int]("background") {
      object color extends Property[Color]("background-color")
    }
  }

}

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

Pseudo-Selectors
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

Pseudo-Elements
  ::after
  ::backdrop
  ::before
  ::first-letter
  ::first-line
  ::selection

Other / Unknown
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

Properties

  align-content
  align-items
  align-self
  all
  animation
  animation-delay
  animation-direction
  animation-duration
  animation-fill-mode
  animation-iteration-count
  animation-name
  animation-play-state
  animation-timing-function

  backface-visibility
  background
  background-attachment
  background-blend-mode
  background-clip
  background-color
  background-image
  background-origin
  background-position
  background-repeat
  background-size
  block-size
  border
  border-block-end
  border-block-end-color
  border-block-end-style
  border-block-end-width
  border-block-start
  border-block-start-color
  border-block-start-style
  border-block-start-width
  border-bottom
  border-bottom-color
  border-bottom-left-radius
  border-bottom-right-radius
  border-bottom-style
  border-bottom-width
  border-collapse
  border-color
  border-image
  border-image-outset
  border-image-repeat
  border-image-slice
  border-image-source
  border-image-width
  border-inline-end
  border-inline-end-color
  border-inline-end-style
  border-inline-end-width
  border-inline-start
  border-inline-start-color
  border-inline-start-style
  border-inline-start-width
  border-left
  border-left-color
  border-left-style
  border-left-width
  border-radius
  border-right
  border-right-color
  border-right-style
  border-right-width
  border-spacing
  border-style
  border-top
  border-top-color
  border-top-left-radius
  border-top-right-radius
  border-top-style
  border-top-width
  border-width
  bottom
  box-decoration-break
  box-shadow
  box-sizing
  break-after
  break-before
  break-inside

  caption-side
  clear
  clip
  clip-path
  color
  column-count
  column-fill
  column-gap
  column-rule
  column-rule-color
  column-rule-style
  column-rule-width
  column-span
  column-width
  columns
  content
  counter-increment
  counter-reset
  cursor

  direction
  display

  empty-cells

  filter
  flex
  flex-basis
  flex-direction
  flex-flow
  flex-grow
  flex-shrink
  flex-wrap
  float
  font
  font-family
  font-feature-settings
  font-kerning
  font-language-override
  font-size
  font-size-adjust
  font-stretch
  font-style
  font-synthesis
  font-variant
  font-variant-alternates
  font-variant-caps
  font-variant-east-asian
  font-variant-ligatures
  font-variant-numeric
  font-variant-position
  font-weight

  grid
  grid-area
  grid-auto-columns
  grid-auto-flow
  grid-auto-rows
  grid-column
  grid-column-end
  grid-column-gap
  grid-column-start
  grid-gap
  grid-row
  grid-row-end
  grid-row-gap
  grid-row-start
  grid-template
  grid-template-areas
  grid-template-columns
  grid-template-rows

  height
  hyphens
  hz

  image-orientation
  image-rendering
  image-resolution
  ime-mode
  in
  inherit
  initial
  inline-size
  isolation

  justify-content

  khz

  left
  letter-spacing
  line-break
  line-height
  list-style
  list-style-image
  list-style-position
  list-style-type

  margin
  margin-block-end
  margin-block-start
  margin-bottom
  margin-inline-end
  margin-inline-start
  margin-left
  margin-right
  margin-top
  mask
  mask-clip
  mask-composite
  mask-image
  mask-mode
  mask-origin
  mask-position
  mask-repeat
  mask-size
  mask-type
  max-block-size
  max-height
  max-inline-size
  max-width
  min-block-size
  min-height
  min-inline-size
  min-width
  mix-blend-mode

  object-fit
  object-position
  offset-block-end
  offset-block-start
  offset-inline-end
  offset-inline-start
  opacity
  order
  orphans
  outline
  outline-color
  outline-offset
  outline-style
  outline-width
  overflow
  overflow-wrap
  overflow-x
  overflow-y

  padding
  padding-block-end
  padding-block-start
  padding-bottom
  padding-inline-end
  padding-inline-start
  padding-left
  padding-right
  padding-top
  page-break-after
  page-break-before
  page-break-inside
  perspective
  perspective-origin
  pointer-events
  position

  quotes

  resize
  revert
  right
  ruby-align
  ruby-merge
  ruby-position

  scroll-behavior
  scroll-snap-coordinate
  scroll-snap-destination
  scroll-snap-type
  shape-image-threshold
  shape-margin
  shape-outside

  tab-size
  table-layout
  text-align
  text-align-last
  text-combine-upright
  text-decoration
  text-decoration-color
  text-decoration-line
  text-decoration-style
  text-emphasis
  text-emphasis-color
  text-emphasis-position
  text-emphasis-style
  text-indent
  text-orientation
  text-overflow
  text-rendering
  text-shadow
  text-transform
  text-underline-position
  top
  touch-action
  transform
  transform-box
  transform-origin
  transform-style
  transition
  transition-delay
  transition-duration
  transition-property
  transition-timing-function

  unicode-bidi
  unset

  vertical-align
  visibility

  white-space
  widows
  width
  will-change
  word-break
  word-spacing
  word-wrap
  writing-mode

  z-index

 */

