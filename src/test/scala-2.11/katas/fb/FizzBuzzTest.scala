package katas.fb

/**
  * Created by sprengerjo on 30/11/2016.
  */

import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class FizzBuzzTest extends FlatSpec with Matchers with BeforeAndAfterEach {


  "fizz buzz up zo" should "return values up 10 15" in {

    FizzBuzz.upTo(15) should be(List("1", "2", "fizz", "4", "buzz", "fizz", "7", "8", "fizz",
      "buzz", "11", "fizz", "13", "14", "fizzbuzz"))

  }

}
