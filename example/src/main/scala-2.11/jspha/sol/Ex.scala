package jspha.sol

class Ex[Sty, Mod](syn: Syntax[Sty, Mod]) {
  import syn._
  import Properties._

  val x = classNamed("foo")(
    background := 3,
    background.color := Color.Names.black
  )
}
