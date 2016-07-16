package jspha.atelier.test.css

import jspha.atelier.css._
import utest._

object PrintExamples extends TestSuite {

  val tests = this {

    'MediaType - {
      val MT = MediaType
      val P = Print[MediaType]
      P.print(MT.All) ==> "all"
      P.print(MT.Screen) ==> "screen"
      P.print(MT.Print) ==> "print"
      P.print(MT.Speech) ==> "speech"
    }

    'MediaQuery - {
      import MediaQuery._
      val P = Print[MediaQuery]
      P.print(OfMediaType(MediaType.All)) ==> "all"
    }

    'Namespace - {
      import Namespace._
      val P = Print[Namespace]
      P.print(Any) ==> "*"
      P.print(Undeclared) ==> ""
      P.print(Named("foobar")) ==> "foobar"
    }

    'SelectorAtom - {
      import SelectorAtom._
      val P = Print[SelectorAtom]
      'id - { P.print(Id("foo")) ==> "#foo" }
      'class - { P.print(Class("foo")) ==> ".foo" }
      'el - {
        'core - { P.print(Element("div")) ==> "div" }
        'ns_any - { P.print(Element("div", ns = Some(Namespace.Any))) ==> "*|div" }
        'ns - { P.print(Element("div", ns = Some(Namespace.Named("foo")))) ==> "foo|div" }
      }
      'universal - {
        def uni(x: Option[Namespace]) = P.print(Universal(x))
        'core - { P.print(Universal()) ==> "*" }
        'ns_undec - { uni(Some(Namespace.Undeclared)) ==> "|*" }
        'ns_any - { uni(Some(Namespace.Any)) ==> "*|*" }
      }
      'attr - {
        val sel0 = Element("p")
        def attr(x: AttrOp) = P.print(Attribute(sel0, x))
        def attri(x: AttrOp) = P.print(Attribute(sel0, x, insensitive = true))
        import AttrOp._
        'present - { attr(Present("foo")) ==> "p[foo]" }
        'equal - { attr(Equal("foo", "bar")) ==> "p[foo=bar]" }
        'contains - { attr(Contains("foo", "bar")) ==> "p[foo~=bar]" }
        'subcode - { attr(SubcodeMatch("foo", "bar")) ==> "p[foo|=bar]" }
        'prefix - { attr(Prefixed("foo", "bar")) ==> "p[foo^=bar]" }
        'suffix - { attr(Suffixed("foo", "bar")) ==> "p[foo$=bar]" }
        'substring - { attr(Substring("foo", "bar")) ==> "p[foo*=bar]" }

        'i_present - { attri(Present("foo")) ==> "p[foo i]" }
        'i_equal - { attri(Equal("foo", "bar")) ==> "p[foo=bar i]" }
        'i_contains - { attri(Contains("foo", "bar")) ==> "p[foo~=bar i]" }
        'i_subcode - { attri(SubcodeMatch("foo", "bar")) ==> "p[foo|=bar i]" }
        'i_prefix - { attri(Prefixed("foo", "bar")) ==> "p[foo^=bar i]" }
        'i_suffix - { attri(Suffixed("foo", "bar")) ==> "p[foo$=bar i]" }
        'i_substring - { attri(Substring("foo", "bar")) ==> "p[foo*=bar i]" }
      }
    }

    'Selector - {
      import Selector._
      import SelectorAtom._
      val P = Print[Selector]
      P.print(Atom(Universal())) ==> "*"
      P.print(Atom(Universal()) > Atom(Universal())) ==> "*>*"
      P.print(Atom(Universal()) + Atom(Universal())) ==> "*+*"
      P.print(Atom(Universal()) ~ Atom(Universal())) ==> "*~*"
      P.print(Atom(Universal()) >> Atom(Universal())) ==> "* *"
      P.print(Atom(Universal()) >> Atom(Universal()) >> Atom(Universal())) ==> "* * *"
    }

    'Rules - {
      val P = Print[Rules]
      'empty - {
        val ex = Rules(Map())
        P.print(ex) ==> ""
      }
      'single - {
        val ex = Rules(Map("foo" -> "bar"))
        P.print(ex) ==> "foo: bar"
      }
      'multiple - {
        val ex = Rules(Map("foo" -> "bar", "baz" -> "quux"))
        P.print(ex) ==> "foo: bar; baz: quux"
      }

    }

    'Decl - {

      import Decl._
      val P = Print[Decl]

      val selection: Decl =
        Selection(
          selectors = SelectorAtom.Id("foo"),
          style = Rules(Map(
            "foo" -> "bar",
            "baz" -> "quux"
          ))
        )

      'Selection - {
        P.print(selection) ==> "#foo { foo: bar; baz: quux }"
      }

      'FontFamily - {
        val ff = FontFamily(Rules(Map(
          "font" -> "fontfont",
          "url" -> "http://example.com/font.otf"
        )))
        P.print(ff) ==>
          "@font-family { font: fontfont; url: http://example.com/font.otf }"
      }

      'Keyframes - {
        val rules1 = Rules(Map("foo" -> "bar"))
        val rules2 = Rules(Map("baz" -> "quux"))
        val kf = Keyframes("name", Map("10%" -> rules1, "80%" -> rules2))

        P.print(kf) ==>
          "@keyframes name { 10% { foo: bar } 80% { baz: quux } }"
      }

      'Media - {
        val mq = MediaQuery.OfMediaType(MediaType.All)
        val media = Media(mq, Sheet(Seq(selection)))

        P.print(media) ==>
          "@media all { #foo { foo: bar; baz: quux } }"

      }

    }

  }

}
