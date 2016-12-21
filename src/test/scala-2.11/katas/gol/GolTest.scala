package katas.gol

/**
  * Created by sprengerjo on 30/11/2016.
  */

import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class GolTest extends FlatSpec with Matchers with BeforeAndAfterEach {

  var gol: GameOfLife = _

  override def beforeEach(): Unit = {
    gol = new GameOfLife()
  }

  "gol" should "print world" in {

    val blinker = List(Cell(0, 1), Cell(1, 1), Cell(2, 1))

    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      //all printlns in this block will be redirected
      gol.print(blinker, 2, 3)
    }

    stream.toString should be(
      "n = 0\n-x-\n-x-\n-x-\n" +
        "n = 1\n---\nxxx\n---\n" +
        "n = 2\n-x-\n-x-\n-x-\n")
  }

  "nextGeneration of blinker" should "return next set of living cells" in {
    val blinker = List(Cell(0, 1), Cell(1, 1), Cell(2, 1))

    val actual = gol.nextGeneration(blinker)

    actual should contain theSameElementsAs List(Cell(1, 0), Cell(1, 1), Cell(1, 2))
  }

  "rules" should "be applied to dead cells" in {
    val isAlive = false

    val actual = List.tabulate(9)(CellStatus(isAlive, _))
      .map(gol.isAliveInNextGeneration(_))

    actual should be(List(false, false, false, true, false, false, false, false, false))
  }

  "rules" should "be applied to living cells" in {
    val isAlive = true

    val actual = List.tabulate(9)(CellStatus(isAlive, _))
      .map(gol.isAliveInNextGeneration(_))

    actual should be(List(false, false, true, true, false, false, false, false, false))
  }

  "neigbours of 1/1" should "be identified" in {
    val expected = List(
      Cell(0, 0),
      Cell(1, 0),
      Cell(2, 0),
      Cell(0, 1),
      Cell(2, 1),
      Cell(0, 2),
      Cell(1, 2),
      Cell(2, 2))

    val actual = gol.getNeighbours(Cell(1, 1))

    actual should contain theSameElementsAs expected
  }

  "neigbours of 9/9" should "be identified" in {
    val expected = List(
      Cell(8, 8),
      Cell(9, 8),
      Cell(10, 8),
      Cell(8, 9),
      Cell(10, 9),
      Cell(8, 10),
      Cell(9, 10),
      Cell(10, 10))

    val actual = gol.getNeighbours(Cell(9, 9))

    actual should contain theSameElementsAs expected
  }

}
