package jspha.sol.types

import jspha.sol.internal.CssValue

sealed trait Angle

object Angle {

  case class Deg(a: Double) extends Angle
  case class Grad(a: Double) extends Angle
  case class Rad(a: Double) extends Angle
  case class Turn(a: Double) extends Angle

  implicit val angleIsCssValue = new CssValue[Angle] {
    def cssRepr(angle: Angle) = angle match {
      case Deg(a) => s"${a}deg"
      case Grad(a) => s"${a}grad"
      case Rad(a) => s"${a}rad"
      case Turn(a) => s"${a}turn"
    }
  }

  def deg(self: Double) = Deg(self)
  def grad(self: Double) = Grad(self)
  def rad(self: Double) = Rad(self)
  def turn(self: Double) = Turn(self)

}
