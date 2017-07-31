package com.yqc.akka.dispatcher

import akka.actor.Actor

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
      Future {
        Thread.sleep(5000)
        println(s"Blocking future finished ${i}")
      }
  }
}