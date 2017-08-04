package com.yqc.akka.router

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

/**
  * Created by yangqc on 2017/8/4
  */
class BroadcastActor extends Actor {

  /**
    * 配置路由actor
    */
  val router13: ActorRef = context.actorOf(FromConfig.props(Props[ChildActor]), "router13")

  override def receive: Receive = {
    case x => router13 ! x
  }
}

object RouterTest5 {
  def main(args: Array[String]): Unit = {
    val system: ActorSystem = ActorSystem("system", ConfigFactory.load("router.conf").getConfig("router4"))
    val actorRef: ActorRef = system.actorOf(Props[BroadcastActor], "parent")
    actorRef ! "this is broadcast"
  }
}
