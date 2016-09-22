import akka.actor.ActorRef

/**
  * Created by sprengerjo on 15/09/16.
  */

import akka.actor.{Actor, ActorSystem, Props}

case class PingMessage(rolls: Int)

case object PongMessage

case object StartMessage

case object StopMessage

class Player(pong: ActorRef) extends Actor {
  var count = 0

  def incrementAndPrint: Int = {
    count += 1;
    val r = scala.util.Random
    val pins = r.nextInt(11);
    println("roll" + pins)
    pins
  }

  def receive = {
    case StartMessage =>
      pong ! PingMessage(incrementAndPrint)
    case PongMessage =>
      if (count > 99) {
        sender ! StopMessage
        context.stop(self)
      } else {
        sender ! PingMessage(incrementAndPrint)
      }
    case _ => println("Ping got something unexpected.")
  }
}

class BowlingCalculatorActor extends Actor {
  def receive = {
    case PingMessage(pins) =>
      println(" got " + pins)
      sender ! PongMessage
    case StopMessage =>
      println("pong stopped")
      context.stop(self)
    case _ => println("Pong got something unexpected.")
  }
}

object Main extends App {
  val system = ActorSystem("PingPongSystem")
  val pong = system.actorOf(Props[BowlingCalculatorActor], name = "pong")
  val ping = system.actorOf(Props(new Player(pong)), name = "ping")
  // start the action
  ping ! StartMessage
  // commented-out so you can see all the output
  //system.shutdown
}