package jspha.sol.dsl

import jspha.sol.internal.Builder

/**
  * Typeclass containing interpretation of certain types as a fragment of CSS
  * language. These are represented as a builder-transformer enabling fast
  * string construction.
  */
trait CssFragment[T] {
  def write(self: T): Builder
  def show(self: T): String = write(self).write
}

object CssFragment {
  def apply[T](implicit ev: CssFragment[T]): CssFragment[T] = ev

  def write[T: CssFragment](a: T) =
    apply[T].write(a)
}
