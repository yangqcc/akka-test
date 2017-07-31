package com.yqc.akka.future

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.concurrent.Future

/**
  * Created by yangqc on 2017/7/31
  */
class A extends Actor {

  import context.dispatcher

  val f = Future("hello")

  override def receive: Receive = {
    case _ => println("nothing")
  }
}

object FutureTest1 {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("actorSystem")
    val aRef: ActorRef = system.actorOf(Props[A], "aRef")
    //    val future = aRef ? "message"
    //    val result = Await.result()
  }
}
