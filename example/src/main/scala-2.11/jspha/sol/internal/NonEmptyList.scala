package jspha.sol.internal

/**
  * Created by tel on 6/26/16.
  */
case class NonEmptyList[T](head: T, tail: Seq[T]) {

  def seq: Seq[T] = head +: tail

  def +:(newHead: T): NonEmptyList[T] =
    NonEmptyList(
      head = newHead,
      tail = seq
    )

  def :+(newEnd: T): NonEmptyList[T] =
    copy(tail = tail :+ newEnd)

  def ++(newTail: NonEmptyList[T]): NonEmptyList[T] =
    copy(tail = tail ++ seq)

}

object NonEmptyList {
  def apply[T](head: T, rest: T*) =
    NonEmptyList(head, rest)
}
