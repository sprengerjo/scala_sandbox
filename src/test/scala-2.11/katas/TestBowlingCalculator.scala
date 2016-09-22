package katas

import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

/**
  * Created by sprengerjo on 21/09/16.
  */
class TestBowlingCalculator extends FlatSpec with Matchers with BeforeAndAfterEach {

  var g: BowlingCalculator = _

  override def beforeEach(): Unit = g = new BowlingCalculator()

  "gutter game" should "score 0" in {
    val scores = List.fill(20)(0)
    g.calculate(scores) should be(0)
  }

  "one pin rolls only" should "score 20" in {
    val scores = List.fill(20)(1)
    g.calculate(scores) should be(20)
  }

  "spare bonus" should "be added" in {
    val scores = List.fill(18)(4)
    g.calculate(List(4, 6) ::: scores) should be(4 + 6 + 4 + 18 * 4)
  }

  "strike bonus" should "be added" in {
    val scores = List.fill(18)(4)
    g.calculate(10 :: scores) should be(10 + 4 + 4 + 18 * 4)
  }

  "perfect games" should "score 3000" in {
    val scores = List.fill(12)(10)
    g.calculate(scores) should be(300)
  }
}
