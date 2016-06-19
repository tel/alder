package jspha.sol.internal


sealed trait Selector[-A] {
  def gen(a: A): String
}

object Selector {

  type Local = Selector[Name]
  type Global = Selector[Any]

  sealed trait Ns

  object Ns {
    case object Default extends Ns
    case object Any extends Ns
    case class SomeNs(ns: String) extends Ns
  }

  case class Element(element: String, ns: Ns = Ns.Default) extends Selector[Any] {
    def gen(a: Any) = ns match {
      case Ns.Default => element
      case Ns.Any => s"*|$element"
      case Ns.SomeNs(nsName) => s"$nsName|$element"
    }
  }

  object Element {
    object Html {
      val div: Selector[Any] = Element("div")
      val menu: Selector[Any] = Element("menu")
      val h5: Selector[Any] = Element("h5")
      val bdi: Selector[Any] = Element("bdi")
      val ul: Selector[Any] = Element("ul")
      val script: Selector[Any] = Element("script")
      val ruby: Selector[Any] = Element("ruby")
      val keygen: Selector[Any] = Element("keygen")
      val dialog: Selector[Any] = Element("dialog")
      val menuitem: Selector[Any] = Element("menuitem")
      val q: Selector[Any] = Element("q")
      val dl: Selector[Any] = Element("dl")
      val aside: Selector[Any] = Element("aside")
      val meter: Selector[Any] = Element("meter")
      val `object`: Selector[Any] = Element("`")
      val ol: Selector[Any] = Element("ol")
      val button: Selector[Any] = Element("button")
      val tbody: Selector[Any] = Element("tbody")
      val h2: Selector[Any] = Element("h2")
      val blockquote: Selector[Any] = Element("blockquote")
      val b: Selector[Any] = Element("b")
      val input: Selector[Any] = Element("input")
      val legend: Selector[Any] = Element("legend")
      val `var`: Selector[Any] = Element("`")
      val address: Selector[Any] = Element("address")
      val tr: Selector[Any] = Element("tr")
      val footer: Selector[Any] = Element("footer")
      val mark: Selector[Any] = Element("mark")
      val base: Selector[Any] = Element("base")
      val li: Selector[Any] = Element("li")
      val samp: Selector[Any] = Element("samp")
      val kbd: Selector[Any] = Element("kbd")
      val span: Selector[Any] = Element("span")
      val progress: Selector[Any] = Element("progress")
      val rt: Selector[Any] = Element("rt")
      val form: Selector[Any] = Element("form")
      val data: Selector[Any] = Element("data")
      val s: Selector[Any] = Element("s")
      val h4: Selector[Any] = Element("h4")
      val source: Selector[Any] = Element("source")
      val em: Selector[Any] = Element("em")
      val sub: Selector[Any] = Element("sub")
      val link: Selector[Any] = Element("link")
      val article: Selector[Any] = Element("article")
      val abbr: Selector[Any] = Element("abbr")
      val summary: Selector[Any] = Element("summary")
      val strong: Selector[Any] = Element("strong")
      val small: Selector[Any] = Element("small")
      val dt: Selector[Any] = Element("dt")
      val section: Selector[Any] = Element("section")
      val details: Selector[Any] = Element("details")
      val p: Selector[Any] = Element("p")
      val a: Selector[Any] = Element("a")
      val h1: Selector[Any] = Element("h1")
      val main: Selector[Any] = Element("main")
      val body: Selector[Any] = Element("body")
      val pre: Selector[Any] = Element("pre")
      val meta: Selector[Any] = Element("meta")
      val wbr: Selector[Any] = Element("wbr")
      val hgroup: Selector[Any] = Element("hgroup")
      val ins: Selector[Any] = Element("ins")
      val col: Selector[Any] = Element("col")
      val header: Selector[Any] = Element("header")
      val select: Selector[Any] = Element("select")
      val code: Selector[Any] = Element("code")
      val thead: Selector[Any] = Element("thead")
      val area: Selector[Any] = Element("area")
      val embed: Selector[Any] = Element("embed")
      val colgroup: Selector[Any] = Element("colgroup")
      val th: Selector[Any] = Element("th")
      val title: Selector[Any] = Element("title")
      val textarea: Selector[Any] = Element("textarea")
      val audio: Selector[Any] = Element("audio")
      val tfoot: Selector[Any] = Element("tfoot")
      val rp: Selector[Any] = Element("rp")
      val video: Selector[Any] = Element("video")
      val param: Selector[Any] = Element("param")
      val nav: Selector[Any] = Element("nav")
      val iframe: Selector[Any] = Element("iframe")
      val i: Selector[Any] = Element("i")
      val sup: Selector[Any] = Element("sup")
      val h3: Selector[Any] = Element("h3")
      val hr: Selector[Any] = Element("hr")
      val noscript: Selector[Any] = Element("noscript")
      val output: Selector[Any] = Element("output")
      val html: Selector[Any] = Element("html")
      val time: Selector[Any] = Element("time")
      val u: Selector[Any] = Element("u")
      val br: Selector[Any] = Element("br")
      val h6: Selector[Any] = Element("h6")
      val label: Selector[Any] = Element("label")
      val big: Selector[Any] = Element("big")
      val del: Selector[Any] = Element("del")
      val track: Selector[Any] = Element("track")
      val table: Selector[Any] = Element("table")
      val figure: Selector[Any] = Element("figure")
      val picture: Selector[Any] = Element("picture")
      val canvas: Selector[Any] = Element("canvas")
      val cite: Selector[Any] = Element("cite")
      val caption: Selector[Any] = Element("caption")
      val figcaption: Selector[Any] = Element("figcaption")
      val dd: Selector[Any] = Element("dd")
      val bdo: Selector[Any] = Element("bdo")
      val optgroup: Selector[Any] = Element("optgroup")
      val fieldset: Selector[Any] = Element("fieldset")
      val datalist: Selector[Any] = Element("datalist")
      val img: Selector[Any] = Element("img")
      val option: Selector[Any] = Element("option")
      val style: Selector[Any] = Element("style")
      val td: Selector[Any] = Element("td")
      val dfn: Selector[Any] = Element("dfn")
      val map: Selector[Any] = Element("map")
      val head: Selector[Any] = Element("head")
    }
    object Svg {
      val text: Selector = Element("text")
      val circle: Selector = Element("circle")
      val stop: Selector = Element("stop")
      val defs: Selector = Element("defs")
      val svg: Selector = Element("svg")
      val radialGradient: Selector = Element("radialGradient")
      val clipPath: Selector = Element("clipPath")
      val pattern: Selector = Element("pattern")
      val g: Selector = Element("g")
      val line: Selector = Element("line")
      val linearGradient: Selector = Element("linearGradient")
      val polyline: Selector = Element("polyline")
      val rect: Selector = Element("rect")
      val mask: Selector = Element("mask")
      val polygon: Selector = Element("polygon")
      val image: Selector = Element("image")
      val path: Selector = Element("path")
      val tspan: Selector = Element("tspan")
      val ellipse: Selector = Element("ellipse")
    }
  }

  case class Id(id: String) extends Selector[Any]  {
    def gen(a: Any) = s"#$id"
  }

  case class Class(cls: String) extends Selector[Any] {
    def gen(a: Any) = s".$cls"
  }

  private case class Out[A](rep: A => String) extends Selector[A] {
    def gen(a: A) = rep(a)
  }

   /**
    * The local-name selector. Forces the selection to operate specifically
    * over names and therefore can only appear in Local selector expressions.
    */
  def & : Selector.Local = Out(_.className)

  case class Adjacent[A](left: Selector[A], right: Selector[A]) extends Selector[A] {
    def gen(a: A) = s"${left.gen(a)}+${right.gen(a)}"
  }
  case class Sibling[A](left: Selector[A], right: Selector[A]) extends Selector[A] {
    def gen(a: A) = s"${left.gen(a)}~${right.gen(a)}"
  }
  case class Child[A](left: Selector[A], right: Selector[A]) extends Selector[A] {
    def gen(a: A) = s"${left.gen(a)}>${right.gen(a)}"
  }
  case class Descendant[A](left: Selector[A], right: Selector[A]) extends Selector[A] {
    def gen(a: A) = s"${left.gen(a)} ${right.gen(a)}"
  }

  case class Not[A](selector: Selector[A]) extends Selector[A] {
    def gen(a: A) = selector match {
      case Not(sel) => sel.gen(a)
      case sel => s":not(${sel.gen(a)})"
    }
  }

  case class WithAttributes[A](selector: Selector[A], constr: AttributeConstraint)
    extends Selector[A] {
    def gen(a: A) = s"${selector.gen(a)}${constr.repr}"
  }

  sealed trait AttributeConstraint {
    def repr: String
  }

  object AttributeConstraint {
    case class Has(attr: String) extends AttributeConstraint {
      def repr = s"[$attr]"
    }
    case class Eq(attr: String, value: String) extends AttributeConstraint {
      def repr = s"[$attr=$value]"
    }
    case class InList(attr: String, value: String) extends AttributeConstraint {
      def repr = s"[$attr~=$value]"
    }
    case class EqOrDash(attr: String, value: String) extends AttributeConstraint {
      def repr = s"[$attr|=$value]"
    }
    case class Prefix(attr: String, value: String) extends AttributeConstraint {
      def repr = s"[$attr^=$value]"
    }
    case class Suffix(attr: String, value: String) extends AttributeConstraint {
      def repr = s"[$attr${"$"}=$value]"
    }
    case class Contains(attr: String, value: String) extends AttributeConstraint {
      def repr = s"[$attr*=$value]"
    }
  }

  implicit class SelectorOps[A](self: Selector[A]) {
    def + (other: Selector[A]) = Adjacent(self, other)
    def ~ (other: Selector[A]) = Sibling(self, other)
    def > (other: Selector[A]) = Child(self, other)
    def >> (other: Selector[A]) = Descendant(self, other)
  }

  implicit class AttributeOps(attr: String) {
    import AttributeConstraint._
    def == (value: String): AttributeConstraint = Eq(attr, value)
    def ~= (value: String): AttributeConstraint = InList(attr, value)
    def |= (value: String): AttributeConstraint = EqOrDash(attr, value)
    def ^= (value: String): AttributeConstraint = Prefix(attr, value)
    def =^ (value: String): AttributeConstraint = Prefix(attr, value)
    def *= (value: String): AttributeConstraint = Contains(attr, value)
  }

  sealed trait WriteDirection
  case object Ltr extends WriteDirection
  case object Rtl extends WriteDirection

  abstract class PseudoClass(val cssName: String) extends Selector[Any] {
    def gen(a: Any) = s":$cssName"
  }

  object PseudoClass {

    case object active extends PseudoClass("active")
    case object checked extends PseudoClass("checked")
    case object default extends PseudoClass("default")

    case class dir(direction: WriteDirection) extends PseudoClass("dir") {
      override val cssName = direction match {
        case Ltr => "dir(ltr)"
        case Rtl => "dir(rtl)"
      }
    }

    object dir {
      val ltr = dir(Ltr)
      val rtl = dir(Rtl)
    }

    case object disabled extends PseudoClass("disabled")
    case object empty extends PseudoClass("empty")
    case object fullscreen extends PseudoClass("fullscreen")
    case object first extends PseudoClass("first")
    case object firstChild extends PseudoClass("first-child")
    case object firstOfType extends PseudoClass("first-of-type")
    case object focus extends PseudoClass("focus")
    case object hover extends PseudoClass("hover")
    case object indeterminate extends PseudoClass("indeterminate")
    case object invalid extends PseudoClass("invalid")
    case class lang(language: String) extends PseudoClass(s"lang($language)")
    case object lastChild extends PseudoClass("last-child")
    case object lastOfType extends PseudoClass("last-of-type")

    case object left extends PseudoClass("left")
    case object link extends PseudoClass("link")

    case class nthChild(mod: Int, offset: Int)
      extends PseudoClass(s"nth-child(${mod}n+${offset})")
    object nthChild {
      val even = apply(2, 0)
      val odd = apply(2, 1)
    }
    case class nthLastChild(mod: Int, offset: Int)
      extends PseudoClass(s"nth-last-child(${mod}n+${offset})")
    object nthLastChild {
      val even = apply(2, 0)
      val odd = apply(2, 1)
    }
    case class nthLastOfType(mod: Int, offset: Int)
      extends PseudoClass(s"nth-last-of-type(${mod}n+${offset})")
    object nthLastOfType {
      val even = apply(2, 0)
      val odd = apply(2, 1)
    }
    case class nthOfType(mod: Int, offset: Int)
      extends PseudoClass(s"nth-of-type(${mod}n+${offset})")
    object nthOfType {
      val even = apply(2, 0)
      val odd = apply(2, 1)
    }

    case object onlyChild extends PseudoClass("only-child")
    case object onlyOfType extends PseudoClass("only-of-type")
    case object optional extends PseudoClass("optional")
    case object outOfRange extends PseudoClass("out-of-range")
    case object readOnly extends PseudoClass("read-only")
    case object readWrite extends PseudoClass("read-write")
    case object required extends PseudoClass("required")
    case object right extends PseudoClass("right")
    case object root extends PseudoClass("root")
    case object scope extends PseudoClass("scope")
    case object target extends PseudoClass("target")
    case object unresolved extends PseudoClass("unresolved")
    case object valid extends PseudoClass("valid")
    case object visited extends PseudoClass("visited")
  }

  abstract class PseudoElement(val cssName: String) extends Selector[Any] {
    def gen(a: Any) = s"::$cssName"
  }

  object PseudoElement {
    case object after extends PseudoElement("after")
    case object backdrop extends PseudoElement("backdrop")
    case object before extends PseudoElement("before")
    case object firstLetter extends PseudoElement("first-letter")
    case object firstLine extends PseudoElement("first-line")
    case object selection extends PseudoElement("selection")
  }

}
