package katas.fb

/**
  * Created by sprengerjo on 11.06.17.
  */
object FizzBuzz {

  def n = (i: Int) => i.toString()

  def f = (i: Int) => "fizz"

  def b = (i: Int) => "buzz"

  def fb = (i: Int) => "fizzbuzz"

  def funs = List[(Int) => String](n, n, f, n, b, f, n, n, f, b, n, f, n, n, fb)

  def fizzBuzz = (i: Int) => funs(i % 15)(i + 1)

  def upTo(n: Int): List[String] =
    List.tabulate(n)((i: Int) => fizzBuzz(i))
}
