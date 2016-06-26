package jspha.sol

import jspha.sol.dsl.CssValue
import jspha.sol.internal._

import scala.util.Random

/**
  * A `Stylesheet` allows you to create new styles and eventually write them
  * to the DOM.
  */
class StylesheetOld private(val mode: StylesheetOld.Mode,
                            val nameHere: Option[String],
                            val subNames: Seq[String]) {

  /**
    * Constructs a fresh stylesheet. Set the mode immediately to generate the
    * proper names
    */
  def this(mode: StylesheetOld.Mode = StylesheetOld.Development) =
    this(mode = mode, nameHere = None, subNames = Seq())

  /**
    * Registers a global style in this sheet. Note that a `Global` selector
    * cannot use `&`---the types will prevent you.
    */
  protected def global(selector: Selector.Global)(mods: Mod*): Unit =
    registerGlobal(selector, mods)

  /**
    * Registers a local style in this sheet and returns details to include
    * its class name in an element
    */
  protected def module(name: String)(mods: Mod*): Style = {
    val nonce = Random.alphanumeric.take(4).mkString
    val realName = dsl.Name(mode, prefixNames, name, nonce)
    registerClass(realName, mods)
    Style(mods, realName)
  }

  /**
    * Defines a new CSS rule under knowledge of the local name (passed in).
    * Can be slightly risky! If the local name is not used in the selector
    * expression then this will introduce new *global* rules.
    */
  protected def selected(buildSelector: Selector.Local => Selector.Local)(mods: Mod*): Mod =
    Mod.selected(buildSelector(Selector.&), mods)

  /**
    * Defines some properties which only hold when certain media
    * constraints do.
    */
  protected def withMedia(constraints: MediaConstraint*)(mods: Mod*): Mod =
    Mod.mediaConstrained(constraints, mods)

  /**
    * Defines some properties against a pseudo-class
    */
  protected def pseudoClass(pc: Selectors.PseudoClass)(mods: Mod*): Mod =
    selected(_ >> pc)(mods)

  /**
    * Defines some properties against a pseudo-element
    */
  protected def pseudoElement(pe: Selectors.PseudoElement)(mods: Mod*): Mod =
    selected(_ >> pe)(mods)

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
      local()
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

    @page

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

  object Properties
    extends jspha.sol.dsl.propertySet.CommonProperties
      with jspha.sol.dsl.propertySet.Flex
      with jspha.sol.dsl.propertySet.Border
      with jspha.sol.dsl.propertySet.ListStyle

  val Selectors = Selector
  val PseudoClass = Selector.PseudoClass
  val PseudoElement = Selector.PseudoElement

  implicit class UnconstrainedProperty(key: String) {
    def :=[A: CssValue](value: A) = Mod.assign(key, CssValue.of(value))
    def ::=(value: String) = Mod.assign(key, value)
  }

  class Sub(val subName: String)
    extends StylesheetOld(mode, Some(subName), prefixNames)


  // Types

  val Angle = types.Angle
  val Color = types.Color
  val Length = types.Length
  val Ratio = types.Ratio
  val Resolution = types.Resolution
  val TransformFunction = types.TransformFunction
  val Url = types.Url

  // Private definitions

  private var globalRegistry: Map[Selector.Global, Seq[Mod]] = Map()
  private var classRegistry: Map[dsl.Name, Seq[Mod]] = Map()

  private def registerGlobal(selector: Selector.Global, value: Seq[Mod]): Unit =
    globalRegistry = globalRegistry + (selector -> value)

  private def registerClass(name: dsl.Name, value: Seq[Mod]): Unit =
    classRegistry = classRegistry + (name -> value)

  private lazy val prefixNames =
    nameHere.fold(subNames)(subNames :+ _)

}

object StylesheetOld {

  sealed trait Mode
  case object Development extends Mode
  case object Production extends Mode

}

