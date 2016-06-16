package jspha.sol

class Ex[Sty, Mod](syn: Syntax[Sty, Mod]) {
  import syn._

  val x = classNamed("foo")(
    {
      import Properties.flex._
      mods(
        basis.fitContent,
        direction.row
      )
    }
  )
}
