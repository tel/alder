package jspha.sol.internal

import scala.collection.breakOut

sealed trait Decl {

  def gen: String

}

object Decl {

  case class Rule(selector: Selector.Global,
                  assignments: Map[String, String])
    extends Decl {

    def gen = {
      val selectorString = Selector.genGlobal(selector)
      val rules = assignments.foldLeft(new StringBuilder) {
        case (builder, (prop, value)) =>
          builder
            .append(prop)
            .append(": ")
            .append(value)
            .append("; ")
      }
      val ruleString = rules.mkString
      s"$selectorString { $ruleString }"
    }
  }

  case class MediaBlock(mqs: Seq[MediaConstraint],
                        decls: Seq[Decl])
    extends Decl {

    def gen = {
      val mq = mqs.mkString(", ")
      val block = decls.map(_.gen).mkString(" ")
      s"@media $mq { $block }"
    }

  }

//  def ofMod(sel: Selector.Global, mods: Seq[Mod]): Seq[Decl] = {
//
//  }
//
//  def ofMods(modMap: Map[Selector.Global, Seq[Mod]]): Seq[Decl] = {
//    modMap.flatMap((ofMod _).tupled)(breakOut)
//  }

}
