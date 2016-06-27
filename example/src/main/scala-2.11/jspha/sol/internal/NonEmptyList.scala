package jspha.sol.internal

/**
  * Created by tel on 6/26/16.
  */
class NonEmptyList[T](val head: T, val tail: Seq[T]) {

  def seq: Seq[T] = head +: tail

  def +:(newHead: T): NonEmptyList[T] =
    new NonEmptyList(
      head = newHead,
      tail = seq
    )

  def :+(newEnd: T): NonEmptyList[T] =
    new NonEmptyList(head = head, tail = tail :+ newEnd)

  def ++(newTail: NonEmptyList[T]): NonEmptyList[T] =
    new NonEmptyList(head = head, tail = tail ++ seq)

}

object NonEmptyList {
  def apply[T](head: T, rest: T*): NonEmptyList[T] =
    new NonEmptyList(head, rest)
}
