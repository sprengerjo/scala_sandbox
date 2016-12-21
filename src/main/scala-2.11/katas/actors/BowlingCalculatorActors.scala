package katas.actors

/**
  * Created by sprengerjo on 15/09/16.
  */


import akka.actor.Actor
import katas.BowlingCalculator

case class ScoreStore(var score: Int) {
  var scores: List[Int] = List()

  def add(newScore: Int): Unit = scores = newScore :: scores
  def addScore(newScore: Int): Unit = score = newScore
}

case class Scored(i: Int)

class ScoreStoreActor(scoreStore: ScoreStore) extends Actor {

  def receive = {
    case Scored(pins: Int) =>

      scoreStore.add(pins)
      scoreStore.addScore(BowlingCalculator.scoreOf(scoreStore.scores))

    case StopMessage =>
      context.stop(self)
    case _ => println("oO got something unexpected.")
  }
}

