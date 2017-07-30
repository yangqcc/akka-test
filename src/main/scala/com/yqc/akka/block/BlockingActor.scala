package com.yqc.akka.block

import akka.actor.Actor

import scala.concurrent.{ExecutionContext, Future}

class BlockingActor extends Actor {

  override def receive: Receive = {
    case i: Int =>
      Thread.sleep(5000)
      print(s"Blocking operation finished:${i}")
  }
}

class BlockingFutureActor extends Actor {
  implicit val executionContext: ExecutionContext = context.dispatcher

  def receive = {
    case i: Int =>
      println(s"Calling blocking Future: ${i}")
      Future {
        Thread.sleep(5000) //block for 5 seconds
        println(s"Blocking future finished ${i}")
      }
  }
}