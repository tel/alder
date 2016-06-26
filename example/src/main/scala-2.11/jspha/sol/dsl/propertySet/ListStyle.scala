package jspha.sol.dsl.propertySet

import jspha.sol.dsl.Name
import jspha.sol.internal.{CounterStyle, Mod, Property}
import jspha.sol.dsl.types.Url

trait ListStyle { this: CommonProperties =>

  /**
    * The list-style property is a shorthand property for setting list-style-type, list-style-image and list-style-position.
    * https://developer.mozilla.org/en-US/docs/Web/CSS/list-style
    */
  object listStyle
    extends Property("list-style")
      with AllGlobalValues
      with NoneValues {

    def apply(builders: (this.type => Mod)*): Mod = builders.map(_(this))

    /**
      * The list-style-position property specifies the position of the marker box in the principal block box.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-position
      */
    object position
      extends Property("list-style-position")
        with AllGlobalValues {

      /**
        * The marker box is the first inline box in the principal block box, after which the element's content flows.
        */
      val inside = this ::= "inside"

      /**
        * The marker box is outside the principal block box.
        */
      val outside = this ::= "outside"

    }

    /**
      * The list-style-image property specifies an image to be used as the list item marker.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-image
      */
    object image
      extends Property("list-style-image")
        with AllGlobalValues
        with NoneValues {

      /**
        * Location of image to use as the marker.
        */
      def apply(url: Url) = this := Url

    }

    /**
      * The list-style-type property specifies the appearance of a list item element. Because it is the only property that defaults to display:list-item, this is usually a <li> element, but can be any element with this display value.
      * https://developer.mozilla.org/en-US/docs/Web/CSS/list-style-type
      */
    object `type`
      extends Property("list-style-type")
        with AllGlobalValues
        with NoneValues {

      /**
        * The specified string will be used as the item's marker.
        */
      def apply(ty: String) = this ::= s"'$ty'"

      /**
        * A identifier for a @counter-style
        */
      def apply(styleName: Name[Name.CounterStyle]) = this := styleName

      // TODO: or an inline one from `symbols()`

      val disc = this ::= "disc"
      val circle = this ::= "circle"
      val square = this ::= "square"
      val decimal = this ::= "decimal"
      val cjkDecimal = this ::= "cjk-decimal"
      val decimalLeadingZero = this ::= "decimal-leading-zero"
      val lowerRoman = this ::= "lower-roman"
      val upperRoman = this ::= "upper-roman"
      val lowerGreek = this ::= "lower-greek"
      val lowerAlpha = this ::= "lower-alpha"
      val lowerLatin = this ::= "lower-latin"
      val upperAlpha = this ::= "upper-alpha"
      val upperLatin = this ::= "upper-latin"
      val arabicIndic = this ::= "arabic-indic"
      val armenian = this ::= "armenian"
      val bengali = this ::= "bengali"
      val cambodian = this ::= "cambodian"
      val cjkEarthlyBranch = this ::= "cjk-earthly-branch"
      val cjkHeavenlyStem = this ::= "cjk-heavenly-stem"
      val cjkIdeographic = this ::= "cjk-ideographic"
      val devanagari = this ::= "devanagari"
      val ethiopicNumeric = this ::= "ethiopic-numeric"
      val georgian = this ::= "georgian"
      val gujarati = this ::= "gujarati"
      val gurmukhi = this ::= "gurmukhi"
      val hebrew = this ::= "hebrew"
      val hiragana = this ::= "hiragana"
      val hiraganaIroha = this ::= "hiragana-iroha"
      val japaneseFormal = this ::= "japanese-formal"
      val japaneseInformal = this ::= "japanese-informal"
      val kannada = this ::= "kannada"
      val katakana = this ::= "katakana"
      val katakanaIroha = this ::= "katakana-iroha"
      val khmer = this ::= "khmer"
      val koreanHangulFormal = this ::= "korean-hangul-formal"
      val koreanHanjaFormal = this ::= "korean-hanja-formal"
      val koreanHanjaInformal = this ::= "korean-hanja-informal"
      val lao = this ::= "lao"
      val lowerArmenian = this ::= "lower-armenian"
      val malayalam = this ::= "malayalam"
      val mongolian = this ::= "mongolian"
      val myanmar = this ::= "myanmar"
      val oriya = this ::= "oriya"
      val persian = this ::= "persian"
      val simpChineseFormal = this ::= "simp-chinese-formal"
      val simpChineseInformal = this ::= "simp-chinese-informal"
      val tamil = this ::= "tamil"
      val telugu = this ::= "telugu"
      val thai = this ::= "thai"
      val tibetan = this ::= "tibetan"
      val tradChineseFormal = this ::= "trad-chinese-formal"
      val tradChineseInformal = this ::= "trad-chinese-informal"
      val upperArmenian = this ::= "upper-armenian"
      val disclosureOpen = this ::= "disclosure-open"
      val disclosureClosed = this ::= "disclosure-closed"

    }

  }

}

