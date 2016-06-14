package jspha.alder.raw

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * A Javascript object wrapping a single opaque item. Used to avoid various
  * shallow merge operations in React. You should probably never see this!
  */
@ScalaJSDefined
trait Singl[A] extends js.Object {
  val value: A
}

/**
  * When passing props to a component one other top-level name is needed:
  * "key". This subtype allows us to transparently support this when needed.
  */
@ScalaJSDefined
trait SinglWithKey[A] extends Singl[A] {
  val key: String
}

object Singl {

  def apply[A](a: A): Singl[A] = new Singl[A] {
    val value = a
  }

  def withKey[A](k: String)(a: A): SinglWithKey[A] = new SinglWithKey[A] {
    val value = a
    val key = k
  }

  def unapply[A](s: Singl[A]): Option[A] = Option[A](s.value)

  def unit: Singl[Unit] = Singl(())

  implicit def anyAIsSingl[A](a: A): Singl[A] = Singl(a)
}
