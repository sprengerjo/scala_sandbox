package katas

/**
  * Created by sprengerjo on 15/01/2017.
  */

object BowlingCalculator {

  def scoreOf(scores: List[Int]): Int =
    scores.reduceLeft(_ + _) + bonus(scores)

  def bonus(scores: List[Int]): Int = scores match {
    case  _ :: _ :: Nil | _ :: _ :: _ :: Nil => 0
    case a :: xs if (a == 10) => xs.head + xs(1) + bonus(xs)
    case a :: b :: xs => if (a + b == 10) xs.head + bonus(xs) else bonus(xs)
  }
}
