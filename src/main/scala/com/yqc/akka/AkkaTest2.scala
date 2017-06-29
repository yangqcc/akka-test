package com.yqc.akka

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by yangqc on 2017/6/29.
  * 父类交给子类完成任务
  */
case class ExecutionMessage(msg: String)

case class StartMessage(msg: String)

case class FinishedMessage(msg: String)

class ChildActor extends Actor {
  override def receive: Receive = {
    case ExecutionMessage(msg) =>
      println(msg)
      sender ! FinishedMessage("kill me")
  }
}

class ParentActor extends Actor {

  val child = context.actorOf(Props[ChildActor], "ChildActor")

  override def receive: Receive = {
    case StartMessage(msg) =>
      println(msg)
      child ! ExecutionMessage("executing child actor")
    case FinishedMessage(msg) =>
      println(msg)
  }
}

object TestApp extends App {

  val system = ActorSystem("testParentChildActors")
  val parentActor = system.actorOf(Props[ParentActor], "ParentActor")

  parentActor ! StartMessage("start")
  //  system.shutdown
}
