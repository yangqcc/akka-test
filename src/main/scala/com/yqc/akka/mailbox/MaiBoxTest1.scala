package com.yqc.akka.mailbox

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.dispatch.{BoundedMessageQueueSemantics, RequiresMessageQueue}

/**
  * Created by yangqc on 2017/8/1
  */
class MyBoundedActor extends Actor with RequiresMessageQueue[BoundedMessageQueueSemantics] {

  override def receive: Receive = {
    case x: Int => println(x)
    case name: String => println(s"name is ${name}")
    case _ => println("nothing!")
  }

}

object MaiBoxTest1 {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("DataInitializer")
    val myBoundedActor: ActorRef = system.actorOf(Props[MyBoundedActor], "myBoundedActor")
    myBoundedActor ! 12
  }
}
