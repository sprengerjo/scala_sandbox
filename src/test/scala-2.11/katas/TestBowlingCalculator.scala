package katas

import org.scalatest.{FlatSpec, Matchers}

class  TestBowling extends FlatSpec with Matchers {

  val scoreOf = BowlingCalculator.scoreOf(_)

  "gutter games" should "score 0" in {
    val rolls = List.fill(20)(0)
    scoreOf(rolls) should be(0)
  }

  "one pin games" should "score 20" in {
    val rolls = List.fill(20)(1)
    scoreOf(rolls) should be(20)
  }

  "one spare bonus" should "be added" in {
    val rolls = List(4, 6) ::: List.fill(18)(4)
    scoreOf(rolls) should be(4 + 6 + 4 + 18 * 4)
  }

  "one strike bonus" should "be added" in {
    val rolls = 10 :: List.fill(18)(4)
    scoreOf(rolls) should be(10 + 4 + 4 + 18 * 4)
  }

  "perfect game" should "score 300" in {
    val rolls = List.fill(12)(10)
    scoreOf(rolls) should be(300)
  }

}