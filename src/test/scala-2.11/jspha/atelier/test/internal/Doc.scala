package jspha.atelier.test.internal

import jspha.atelier.internal.{Doc => X}
import utest._

object Doc extends TestSuite {

  implicit class Assert(d: X) {
    def -->(other: String) = d.build ==> other
  }

  import X._

  val tests = this {

    "nil" - { empty --> "" }
    "const" - { string("foo") --> "foo" }
    "append" - { ("foo" <> "bar") --> "foobar" }
    "cat space" - { ("foo" <+> "bar") --> "foo bar" }
    "lines" - { lines("foo", "bar") --> "foo\nbar" }
    "words" - { words("foo", "bar", "baz") --> "foo bar baz" }
    "braced" - { braced(words("foo", "bar", "baz")) --> "{ foo bar baz }" }
    "parened" - { parened(words("foo", "bar", "baz")) --> "( foo bar baz )" }
    "bracketed" - { bracketed(words("foo", "bar", "baz")) --> "[ foo bar baz ]" }

  }

}
