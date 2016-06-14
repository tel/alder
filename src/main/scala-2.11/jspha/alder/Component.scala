package jspha.alder

import jspha.alder.raw.Singl

/**
  * A nicer version of the raw React component.
  */
trait Component[P, S] {
  val props: Option[P]
  val state: Option[S]

  def setState(newState: S): Unit
  def forceUpdate(): Unit
}

object Component {
  def fromRaw[P, S](core: raw.Component[P, S]): Component[P, S] = new Component[P, S] {
    val props: Option[P] = core.props.toOption.map(_.value)
    val state: Option[S] = core.state.toOption.map(_.value)

    def forceUpdate(): Unit = core.forceUpdate()
    def setState(newState: S): Unit = core.setState(Singl(newState))
  }
}
