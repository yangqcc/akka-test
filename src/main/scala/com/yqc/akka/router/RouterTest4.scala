package com.yqc.akka.router

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

/**
  * Created by yangqc on 2017/8/3
  */
class RouterParent extends Actor {
  val router3: ActorRef = context.actorOf(FromConfig.props(), "router3")
  val childRef1: ActorRef = context.actorOf(Props[ChildActor], "child1")
  val childRef2: ActorRef = context.actorOf(Props[ChildActor], "child2")
  val childRef3: ActorRef = context.actorOf(Props[ChildActor], "child3")

  override def receive: Receive = {
    case _ => router3 ! "this is router3"
  }
}

object RouterTest4 {
  def main(args: Array[String]): Unit = {
    val system: ActorSystem = ActorSystem("system", ConfigFactory.load("router.conf").getConfig("router3"))
    val parentRef: ActorRef = system.actorOf(Props[RouterParent], "parent")
    parentRef ! "this is router3"
    parentRef ! "this is router3"
    parentRef ! "this is router3"
    parentRef ! "this is router3"
  }
}
