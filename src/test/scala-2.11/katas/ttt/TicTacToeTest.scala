import katas._
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class TicTacToeTest extends FlatSpec with Matchers with BeforeAndAfterEach {

  val play = (marker: Marker, index: Int, board: List[Marker]) => TicTacToe.play(marker, index, board)
  val whoHasWon = (board: List[Marker]) => TicTacToe.whoHasWon(board)

  "add an X to an empty board" should "be returned" in {
    val board = List.fill(9)(I)

    val actual = play(X, 0, board)

    actual should be(List(
      X, I, I,
      I, I, I,
      I, I, I))
  }

  "add an O to an empty board" should "be returned" in {
    val board = List.fill(9)(I)

    val actual = play(O, 0, board)

    actual should be(List(
      O, I, I,
      I, I, I,
      I, I, I))
  }

  "add an X to an already set O cell" should "be refused" in {
    val board = List.fill(9)(I)

    val actual = play(O, 0, board)
    val actualAfterNotAllowedPlay = play(X, 0, actual)

    actualAfterNotAllowedPlay should be(List(
      O, I, I,
      I, I, I,
      I, I, I))
  }

  "a full game" should "retunr X as winner" in {
    var board: List[Marker] = List.fill(9)(I)

    board = play(O, 3, board)
    board = play(X, 0, board)
    board = play(O, 4, board)
    board = play(X, 1, board)
    board = play(O, 8, board)
    board = play(X, 2, board)

    whoHasWon(board) should be(X)
  }

  "a full game" should "retunr O as winner" in {
    var board: List[Marker] = List.fill(9)(I)

    board = play(O, 0, board)
    board = play(X, 1, board)
    board = play(O, 4, board)
    board = play(X, 1, board)
    board = play(O, 8, board)

    whoHasWon(board) should be(O)
  }


  "3 vertical" should "return appropriate marker" in {
    whoHasWon(List(
      I, X, I,
      I, X, I,
      I, X, I)) should be(X)

    whoHasWon(List(
      X, I, I,
      X, I, I,
      X, I, I)) should be(X)

    whoHasWon(List(
      I, I, X,
      I, I, X,
      I, I, X)) should be(X)

    whoHasWon(List(
      O, O, X,
      X, O, X,
      O, X, X)) should be(X)
  }

  "no tripples" should "return I" in {
    whoHasWon(List(
      I, I, I,
      I, I, I,
      I, I, I)) should be(I)
  }

  "3 horizontal" should "return appropriate marker" in {
    whoHasWon(List(
      X, X, X,
      I, I, I,
      I, I, I)) should be(X)

    whoHasWon(List(
      I, I, I,
      O, O, O,
      I, I, I)) should be(O)

    whoHasWon(List(
      I, I, I,
      I, I, I,
      O, O, O)) should be(O)
  }

  "3 cross" should "return appropriate marker" in {
    whoHasWon(List(
      X, I, I,
      I, X, I,
      I, I, X)) should be(X)

    whoHasWon(List(
      I, I, O,
      I, O, I,
      O, I, I)) should be(O)
  }

}
