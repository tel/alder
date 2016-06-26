package jspha.sol.dsl

import jspha.sol.internal.{Builder, NonEmptyList}

/**
  * A `MediaQueryDisj` is a list of `MediaQuery`s or negated `MediaQuery`s
  * which itself holds exactly when one of the `MediaQuery`s (or its
  * negation, as appropriate) does.
  */
sealed trait MediaQueryDisj {

  import MediaQueryDisj._

  /**
    * INVARIANT: Must not be passed a Disj.
    */
  private def pair(mqd: MediaQueryDisj): (MediaQuery, Boolean) =
    mqd match {
      case Holds(mq) => (mq, true)
      case Fails(mq) => (mq, false)
      case _ => sys.error("Impossible!")
    }

  def | (other: MediaQueryDisj): MediaQueryDisj =
    this match {
      case Disj(mqs) =>
        other match {
          case Disj(mqsOther) => Disj(mqs ++ mqsOther)
          case _ => Disj(mqs :+ pair(other))
        }
      case _ =>
        other match {
          case Disj(mqsOther) =>
            Disj(pair(this) +: mqsOther)
          case _ => Disj(NonEmptyList(pair(this), pair(other)))
        }
    }

}

object MediaQueryDisj {
  implicit final case class Holds(mq: MediaQuery) extends MediaQueryDisj
  final case class Fails(mq: MediaQuery) extends MediaQueryDisj
  final case class Disj(mqs: NonEmptyList[(MediaQuery, Boolean)]) extends MediaQueryDisj


  val isCssFragment: CssFragment[MediaQueryDisj] = new CssFragment[MediaQueryDisj] {
    def write(self: MediaQueryDisj): Builder =
      self match {
        case Holds(mq) => CssFragment.write(mq)
        case Fails(mq) => Builder.ofString("not ") ++ CssFragment.write(mq)
        case Disj(nel) =>
          Builder.ofSeq(
            f = { case (mq, ok) =>
              if (ok) write(Holds(mq))
              else write(Fails(mq))
            },
            bs = nel.seq,
            sep = " and "
          )
      }
  }
}
