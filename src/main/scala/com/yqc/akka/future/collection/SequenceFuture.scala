package com.yqc.akka.future.collection

import java.util.concurrent.Executors

import akka.actor.{Actor, ActorSystem, Props}
import akka.util.Timeout

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author: yangqc
  * @description:
  * @date: 上午12:13 17-10-15
  */
class CustomActor extends Actor {
  override def receive = {
    case _ => 2
  }
}

object SequenceFuture {

  import scala.concurrent.duration._

  implicit val timeout = Timeout(5 seconds)
  implicit val ec = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("actorSystem")
    val oddActor = system.actorOf(Props[CustomActor], "oddActor")

    val listOfFutures = List.fill(100)(akka.pattern.ask(oddActor, "this is message").mapTo[Int])

    val futureList = Future.sequence(listOfFutures)

    //Find the sum of the odd numbers
    val oddSum = futureList.map(_.sum)
    oddSum foreach println
  }
}
