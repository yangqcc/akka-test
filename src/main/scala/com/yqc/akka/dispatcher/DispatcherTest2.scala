package com.yqc.akka.dispatcher

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by yangqc on 2017/7/31
  */
class BlockingActor extends Actor {
  override def receive: Receive = {
    case i: Int =>
      Thread.sleep(5000)
      println(s"Blocking operation finished:${i}")
  }
}

class BlockingFutureActor extends Actor {

  implicit val executionContext: ExecutionContext = context.dispatcher

  override def receive: Receive = {
    case i: Int => println(s"Calling blocking Future:${i}")
      //异步执行阻塞调用，但是远远没有这么简单
      Future {
        Thread.sleep(5000)
        println(s"Blocking future finished ${i}")
      }
      println("after future!")
  }
}

object TestBlocking {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("system")
    val blockingActor: ActorRef = system.actorOf(Props[BlockingFutureActor], "blockingFutureActor")
    blockingActor ! 21
  }
}