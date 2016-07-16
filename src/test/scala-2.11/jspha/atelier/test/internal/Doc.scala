package jspha.atelier.test.internal

import utest._

import jspha.atelier.internal.Doc._

object Doc extends TestSuite {

  val tests = this {

    "nil" - {
      nil.build ==> ""
    }

    "consts" - {
      "foo".build ==> "foo"
    }

    "append" - {
      ("foo" <> "bar").build ==> "foobar"
    }

    "lines" - {
      lines(Seq("foo", "bar")).build ==> "foo\nbar"
    }

    "cat space" - {
      ("foo" <+> "bar").build ==> "foo bar"
    }

    "words" - {
      words(Seq("foo", "bar", "baz")).build ==> "foo bar baz"
    }

  }

}
