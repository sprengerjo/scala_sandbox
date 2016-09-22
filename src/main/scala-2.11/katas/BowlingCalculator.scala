package katas

/**
  * Created by sprengerjo on 22/09/16.
  */
class BowlingCalculator() {

  def calculate(scores: List[Int]): Int = scores.reduceLeft(_ + _) + bonus(scores)

  def bonus(scores: List[Int]) : Int = {
    if(scores.size <= 3) 0
    else if(scores.head + scores(1) == 10) scores(2) + bonus(scores.drop(2))
    else if(scores.head == 10) scores.tail.take(2).reduceLeft(_ + _ ) + bonus(scores.tail)
    else bonus(scores.drop(2))
  }

}
