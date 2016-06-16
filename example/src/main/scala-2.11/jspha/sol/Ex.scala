package jspha.sol

class Ex[Sty, Mod](syn: Syntax[Sty, Mod]) {
  import syn._
  import syn.{Properties => P}

  val x = classNamed("foo")(
    P.flex.basis.content,
    P.border.left.style.dashed,
    P.border.top.leftRadius := Length.px(10)
  )
}
