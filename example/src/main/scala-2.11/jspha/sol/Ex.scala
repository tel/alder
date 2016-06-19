package jspha.sol

object Ex extends Stylesheet {

  import Properties._

  val x = module("foo")(
    flex.basis.content,
    border.left.style.dashed,
    "foo" ::= "bar",
    justifyContent ::= "foo",
    pseudoClass(PseudoClass.link)(
      border.bottom.color := Color.Names.white
    )
  )

}
