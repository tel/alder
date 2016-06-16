package jspha.sol.types

import jspha.sol.internal.CssValue

sealed trait Length {

  val l: Double
  def apply(l: Double): this.type

  def + (other: this.type): this.type = apply(l + other.l)
  def - (other: this.type): this.type = apply(l - other.l)
  def * (other: Double): this.type = apply(l * other)
  def / (other: Double): this.type = apply(l / other)

}

object Length {

  private case class Em(l: Double) extends Length
  private case class Ex(l: Double) extends Length
  private case class Ch(l: Double) extends Length
  private case class Rem(l: Double) extends Length
  private case class Vh(l: Double) extends Length
  private case class Vw(l: Double) extends Length
  private case class Vmin(l: Double) extends Length
  private case class Vmax(l: Double) extends Length
  private case class Px(l: Double) extends Length
  private case class Mm(l: Double) extends Length
  private case class Q(l: Double) extends Length
  private case class Cm(l: Double) extends Length
  private case class In(l: Double) extends Length
  private case class Pt(l: Double) extends Length
  private case class Pc(l: Double) extends Length

  implicit val lengthIsCssValue = new CssValue[Length] {
    def cssRepr(len: Length) = len match {
      case Em(l) => s"${l}em"
      case Ex(l) => s"${l}ex"
      case Ch(l) => s"${l}ch"
      case Rem(l) => s"${l}rem"
      case Vh(l) => s"${l}vh"
      case Vw(l) => s"${l}vw"
      case Vmin(l) => s"${l}vmin"
      case Vmax(l) => s"${l}vmax"
      case Px(l) => s"${l}px"
      case Mm(l) => s"${l}mm"
      case Q(l) => s"${l}q"
      case Cm(l) => s"${l}cm"
      case In(l) => s"${l}in"
      case Pt(l) => s"${l}pt"
      case Pc(l) => s"${l}pc"
    }
  }

  def em(self: Double) = Em(self)
  def ex(self: Double) = Ex(self)
  def ch(self: Double) = Ch(self)
  def rem(self: Double) = Rem(self)
  def vh(self: Double) = Vh(self)
  def vw(self: Double) = Vw(self)
  def vmin(self: Double) = Vmin(self)
  def vmax(self: Double) = Vmax(self)
  def px(self: Double) = Px(self)
  def mm(self: Double) = Mm(self)
  def q(self: Double) = Q(self)
  def cm(self: Double) = Cm(self)
  def in(self: Double) = In(self)
  def pt(self: Double) = Pt(self)
  def pc(self: Double) = Pc(self)

}