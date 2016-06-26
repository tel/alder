package jspha.sol.dsl.types

import jspha.sol.dsl.CssValue

sealed trait Angle {
  def radians: Angle.Rad
  def cos: Double = Math.cos(radians.a)
  def sin: Double = Math.sin(radians.a)
  def tan: Double = Math.tan(radians.a)
}

object Angle {

  case class Deg(a: Double) extends AnyVal with Angle {
    def radians = Rad(a/360 * 2 * Math.PI)
  }
  case class Grad(a: Double) extends AnyVal with Angle {
    def radians = Rad(a/400 * 2 * Math.PI)
  }
  case class Rad(a: Double) extends AnyVal with Angle {
    def radians = this
  }
  case class Turn(a: Double) extends AnyVal with Angle {
    def radians = Rad(a * 2 * Math.PI)
  }

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
