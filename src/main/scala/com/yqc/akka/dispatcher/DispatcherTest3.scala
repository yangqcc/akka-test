package com.yqc.akka.dispatcher

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by yangqc on 2017/8/15
  */
class ChildDispatcher extends Actor {
  override def receive: Receive = {
    case x@_ => println(x)
  }
}

class DispatcherActor extends Actor {

  println(context.dispatcher)

  override def receive: Receive = {
    case _ => context.actorOf(Props[ChildDispatcher]) ! "this is message!"
  }
}

object DispatcherTest3 {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("system")
    val actorRef = system.actorOf(Props[DispatcherActor].withDispatcher("my-dispatcher"), "myactor1")
    actorRef ! "message!"
  }
}
