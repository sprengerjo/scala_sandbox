package katas.gol

/**
  * Created by sprengerjo on 30/11/2016.
  */


case class CellStatus(isAlive: Boolean, livingNeighbours: Int)

case class Cell(x: Int, y: Int)

class GameOfLife() {

  def print(cells: List[Cell], generation: Int, worldSize: Int): Unit = {

    if (generation > 0) {
      print(nextGeneration(cells), generation - 1, worldSize)
    }

    println("n = " + generation)
    val matrix = Array.tabulate(worldSize, worldSize)((i, j) => {
      if (cells.contains(Cell(i, j))) "x"
      else "-"
    })

    matrix.foreach(x => println(x.mkString))
  }

  def nextGeneration(livingCells: List[Cell]): List[Cell] = {
    livingCells
      .flatMap(getNeighbours)
      .groupBy(identity)
      .mapValues(_.size)
      .filter(e => isAliveInNextGeneration(CellStatus(livingCells.contains(e._1), e._2)))
      .keys
      .toList
  }

  def isAliveInNextGeneration(cell: CellStatus): Boolean = {
    cell.livingNeighbours == 2 && cell.isAlive || cell.livingNeighbours == 3
  }

  def getNeighbours(cell: Cell): List[Cell] = {
    val d = List(-1, 0, 1)

    val neighbours: List[Cell] =
      d.flatMap(dx => {
        d.map(dy => {
          Cell(cell.x - dx, cell.y - dy)
        })
      })

    neighbours.filter(_ != cell)
  }
}
