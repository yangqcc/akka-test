package com.yqc.akka.router

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.{DefaultResizer, RoundRobinPool}

/**
  * Created by yangqc on 2017/8/1
  */
class MyActor extends Actor {

  val router1: ActorRef = context.actorOf(RoundRobinPool(nrOfInstances = 5, resizer = Some(DefaultResizer(5, 7))).props(Props[ChildActor]))

  override def receive: Receive = {
    case _: String =>
      println("this is myActor")
      router1 ! "123"
  }
}

object RouterTest2 {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("system")
    val myActor = system.actorOf(Props[MyActor], "myActor")
    myActor ! "12"
    myActor ! "12"
    myActor ! "12"
    myActor ! "12"
    myActor ! "12"
    myActor ! "12"
    TimeUnit.SECONDS.sleep(5)
    myActor ! "12"
    myActor ! "12"
    myActor ! "12"
    myActor ! "12"
  }
}
