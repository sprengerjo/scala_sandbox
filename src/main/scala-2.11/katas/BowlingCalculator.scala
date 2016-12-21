package katas

object BowlingCalculator {

  def scoreOf(rolls: List[Int]): Int = bonus(rolls) :: rolls reduceLeft(_ +_ )

  private def bonus(rolls: List[Int]): Int = {
    if(rolls.size <= 3) 0
    else if(rolls.head  == 10) rolls(1) + rolls(2) + bonus(rolls.tail)
    else if(rolls.head + rolls(1) == 10) rolls(2) + bonus(rolls.drop(2))
    else bonus(rolls.drop(2))
  }
}