package katas.actors

import akka.actor.ActorRef

/**
  * Created by sprengerjo on 15/09/16.
  */

import akka.actor.{Actor, ActorSystem, Props}

case object PingMessage

case class PongMessage(i: Int)

case class PauseMessage(pong: ActorRef)

case object StopMessage

class PingActor(var pong: ActorRef) extends Actor {
  var count: Int = 0

  def incrementAndPrint: Int = {
    count += 1
   // println(count)
    count
  }

  def receive = {
    case PongMessage =>
      incrementAndPrint
      pong ! PingMessage
    case PauseMessage(newPong) =>
      pong ! StopMessage
      println("ping paused at " + count)
      pong = newPong
    case StopMessage =>
      context.stop(self)
    case _ => println("Ping got something unexpected.")
  }
}

class PongActor extends Actor {

  def receive = {
    case PingMessage =>
      sender ! PongMessage
    case StopMessage =>
      context.stop(self)
      println("pong paused")
    case _ => println("Pong got something unexpected.")
  }
}

object Main extends App {
  val system = ActorSystem("PingPongSystem")
  val pong = system.actorOf(Props[PongActor], name = "pong")
  val ping = system.actorOf(Props(new PingActor(pong)), name = "ping")
  // start the action
  ping ! PongMessage

}
