package jspha.sol.internal

import scala.language.implicitConversions

sealed trait Mod

object Mod {

  case class PropertyAssign(key: String, value: String) extends Mod
  case class Sequence(mods: Seq[Mod]) extends Mod
  case class Include(name: Name) extends Mod
  case class Selected(selector: Selector.Local, mods: Seq[Mod]) extends Mod
  case class ConstrainMedia(media: MediaQuery, mods: Seq[Mod]) extends Mod

  def assign(key: String, value: String): Mod =
    PropertyAssign(key, value)

  def selected(selector: Selector.Local, mods: Seq[Mod]): Mod =
    Selected(selector, mods)

  implicit def sequence(mods: Seq[Mod]): Mod =
    Sequence(mods)

  implicit def include(style: Style): Mod =
    Include(style.name)

}

