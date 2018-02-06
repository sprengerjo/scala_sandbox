package katas

class Marker(sign: String) {
  override def toString: String = sign
}

object X extends Marker("X")

object O extends Marker("O")

object I extends Marker("-")

object TicTacToe {

  def whoHasWon(board: List[Marker]): Marker = {
    val h1 = List(board(0), board(1), board(2))
    val h2 = List(board(3), board(4), board(5))
    val h3 = List(board(6), board(7), board(8))
    val v1 = List(board(0), board(3), board(6))
    val v2 = List(board(1), board(4), board(7))
    val v3 = List(board(2), board(5), board(8))
    val c1 = List(board(0), board(4), board(8))
    val c2 = List(board(6), board(4), board(2))

    val hvs = List(h1, h2, h3, v1, v2, v3, c1, c2)

    hvs
      .filterNot(i => i.contains(I) || i.contains(X) && i.contains(O))
      .headOption.getOrElse(List(I)).head
  }

  def play(marker: Marker, i: Int, board: List[Marker]): List[Marker] = {
    if (board(i) == I) board.updated(i, marker)
    else board
  }

}
