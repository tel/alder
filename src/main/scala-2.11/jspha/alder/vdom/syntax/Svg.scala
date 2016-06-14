package jspha.alder.vdom.syntax

/**
  * Created by tel on 5/31/16.
  */
class Svg[El, Mod](syntax: Internal[El, Mod]) {

  import syntax.makeFactory

  def Attrs = new SvgAttrs(syntax)

  def text(mods: Mod*): El = makeFactory("text")(mods:_*)
  def circle(mods: Mod*): El = makeFactory("circle")(mods:_*)
  def stop(mods: Mod*): El = makeFactory("stop")(mods:_*)
  def defs(mods: Mod*): El = makeFactory("defs")(mods:_*)
  def svg(mods: Mod*): El = makeFactory("svg")(mods:_*)
  def radialGradient(mods: Mod*): El = makeFactory("radialGradient")(mods:_*)
  def pattern(mods: Mod*): El = makeFactory("pattern")(mods:_*)
  def clipPath(mods: Mod*): El = makeFactory("clipPath")(mods:_*)
  def g(mods: Mod*): El = makeFactory("g")(mods:_*)
  def line(mods: Mod*): El = makeFactory("line")(mods:_*)
  def linearGradient(mods: Mod*): El = makeFactory("linearGradient")(mods:_*)
  def polyline(mods: Mod*): El = makeFactory("polyline")(mods:_*)
  def rect(mods: Mod*): El = makeFactory("rect")(mods:_*)
  def mask(mods: Mod*): El = makeFactory("mask")(mods:_*)
  def polygon(mods: Mod*): El = makeFactory("polygon")(mods:_*)
  def image(mods: Mod*): El = makeFactory("image")(mods:_*)
  def path(mods: Mod*): El = makeFactory("path")(mods:_*)
  def tspan(mods: Mod*): El = makeFactory("tspan")(mods:_*)
  def ellipse(mods: Mod*): El = makeFactory("ellipse")(mods:_*)

}
