package com.yqc.akka.actors

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by yangqc on 2017/7/1.
  */
class StopActor extends Actor {5

  var i: Int = 0

  override def receive: Receive = {
    case "stop" => context.stop(self)
    case _ =>
      i = i + 1
      println(i)
  }
}

object StopActor {

  def props(): Props = Props(classOf[StopActor])
}

object TestStopActor {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("stopSystem")
    val stopActor = system.actorOf(StopActor.props(), "stopActor")

    for (i <- 0 to 10) {
      stopActor ! "message" + i
    }

    stopActor ! "stop"

    Thread.sleep(10000)
    stopActor ! "xx"
  }
}
