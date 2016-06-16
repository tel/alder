package jspha.sol

class Ex[Sty, Mod](syn: Syntax[Sty, Mod]) {
  import syn._
  import Properties._

  val x = classNamed("foo")(
    flex.basis.content,
    border.left.style.dashed
  )
}
