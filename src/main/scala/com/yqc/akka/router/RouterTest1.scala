package com.yqc.akka.router

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorRef, ActorSystem, Kill, Props, Terminated}
import akka.routing.{ActorRefRoutee, RoundRobinRoutingLogic, Router}

/**
  * akka 路由
  * Created by yangqc on 2017/8/1
  */
class ChildActor extends Actor {
  override def receive: Receive = {
    case w: String =>
      println(s"child,value is ${w}")
      println(self)
      self ! Kill
  }
}

class Master extends Actor {

  var childActor: ActorRef = null

  var router = {
    val routees = Vector.fill(5) {
      childActor = context.actorOf(Props[ChildActor])
      context watch childActor
      ActorRefRoutee(childActor)
    }
    Router(RoundRobinRoutingLogic(), routees)
  }

  override def receive: Receive = {
    case w: String => router.route(w, childActor)
    case Terminated(a) =>
      println("father terminated!")
      router = router.removeRoutee(a)
      childActor = context.actorOf(Props[ChildActor])
      context watch childActor
      router = router.addRoutee(childActor)
  }
}

object RouterTest1 {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("system")
    val masterRef = system.actorOf(Props[Master], "masterRef")
    masterRef ! "12"
    TimeUnit.SECONDS.sleep(6)
    masterRef ! "2121"
  }
}
