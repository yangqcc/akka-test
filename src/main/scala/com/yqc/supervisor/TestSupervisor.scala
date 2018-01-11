package com.yqc.supervisor

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import com.yqc.exception.CustomException

object TestSupervisor {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("testSystem", ConfigFactory.load("application.conf"))
    val customActor = system.actorOf(CustomActor.props(), "customActor")
    for (i <- 0 to 3) {
      customActor ! "exception"
    }
    customActor ! "message"
    customActor ! "message"
  }
}

class CustomActor extends Actor {
  override def receive: Receive = {
    case "exception" => throw CustomException("")
    case message => print(message)
  }

  override def preStart(): Unit = {
    super.preStart()
    println("+=========================")
  }
}

object CustomActor {
  def props(): Props = Props(classOf[CustomActor])
}
