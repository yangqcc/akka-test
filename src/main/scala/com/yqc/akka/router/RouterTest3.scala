package com.yqc.akka.router

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

/**
  * Created by yangqc on 2017/8/3
  */
class RouterActor extends Actor {
  val router1: ActorRef = context.actorOf(FromConfig.props(Props[ChildActor]), "router1")

  override def receive: Receive = {
    case _: String => router1 ! "to route"
  }
}

object RouterTest3 {
  def main(args: Array[String]): Unit = {
    val system: ActorSystem = ActorSystem("system", ConfigFactory.load("router.conf"))
    val routerActor: ActorRef = system.actorOf(Props[RouterActor], "parent")
    routerActor ! "hello"
  }
}
