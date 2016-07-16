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
      P.print(Named("foobar")) ==> "foobar"
    }

    'Selector - {
      import Selector._
      val P = Print[Selector]
      P.print(Id("foo")) ==> "#foo"
      P.print(Class("foo")) ==> ".foo"
      P.print(Element("div")) ==> "div"
      P.print(Element("div", ns = Some(Namespace.Any))) ==> "*|div"
      P.print(Element("div", ns = Some(Namespace.Named("foo")))) ==> "foo|div"
    }

    'Rules - {
      val P = Print[Rules]
      'empty - {
        val ex = Rules(Map())
//        P.print(ex) ==> ""
        P.print(ex) ==> ""
      }
      'single - {
        val ex = Rules(Map("foo" -> "bar"))
//        P.print(ex) ==> "foo: bar"
        P.print(ex) ==> "  foo: bar"
      }
      'multiple - {
        val ex = Rules(Map("foo" -> "bar", "baz" -> "quux"))
//        P.print(ex) ==> "foo: bar; baz: quux"
        P.print(ex) ==> "  foo: bar;\n  baz: quux"
      }

    }

    'Decl - {

      import Decl._
      val P = Print[Decl]

      val selection: Decl =
        Selection(
          selector = Selector.Id("foo"),
          style = Rules(Map(
            "foo" -> "bar",
            "baz" -> "quux"
          ))
        )

      'Selection - {
//        P.print(selection) ==> "#foo { foo: bar; baz: quux }"
        P.print(selection) ==>
          """#foo {
            |  foo: bar;
            |  baz: quux
            |}""".stripMargin
      }

      'FontFamily - {
        val ff = FontFamily(Rules(Map(
          "font" -> "fontfont",
          "url" -> "http://example.com/font.otf"
        )))
//        P.print(ff) ==>
//          "@font-family { font: fontfont; url: http://example.com/font.otf }"
        P.print(ff) ==>
          """@font-family {
            |  font: fontfont;
            |  url: http://example.com/font.otf
            |}""".stripMargin
      }

      'Keyframes - {
        val rules1 = Rules(Map("foo" -> "bar"))
        val rules2 = Rules(Map("baz" -> "quux"))
        val kf = Keyframes("name", Map("10%" -> rules1, "80%" -> rules2))

//        P.print(kf) ==>
//          "@keyframes name { 10% { foo: bar } 80% { baz: quux } }"

        // TODO: Change this formatting to stack initial spaces
        P.print(kf) ==>
          """@keyframes name {
            |10% {
            |  foo: bar
            |}
            |80% {
            |  baz: quux
            |}
            |}""".stripMargin
      }

      'Media - {
        val mq = MediaQuery.OfMediaType(MediaType.All)
        val media = Media(mq, Sheet(Seq(selection)))

//        P.print(media) ==>
//          "@media all { #foo { foo: bar; baz: quux } }"

        // TODO: Change this formatting to stack initial spaces
        P.print(media) ==>
          """@media all {
            |#foo {
            |  foo: bar;
            |  baz: quux
            |}
            |}""".stripMargin

      }

    }

  }

}
