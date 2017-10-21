package com.yqc.akka.future.actor

import java.util.concurrent.Executors

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

/**
  * Created by yangqc on 17-10-21
  */
final case class Result(x: Int, s: String, d: Double)

case object Request

class IntActor extends Actor {
  override def receive = {
    case _ => sender() ! 12
  }
}

class StringActor extends Actor {
  override def receive = {
    case _ => sender() ! "yes!"
  }
}

class DoubleActor extends Actor {
  override def receive = {
    case _ => sender() ! 12d
  }
}

object SendAndReceiveFuture {


  implicit val timeout = Timeout(5 seconds)
  private implicit val ec = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(2))

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("system")
    val intActor = system.actorOf(Props[IntActor], "intActor")
    val stringActor = system.actorOf(Props[StringActor], "stringActor")
    val doubleActor = system.actorOf(Props[DoubleActor], "doubleActor")
    val f: Future[Result] =
      for {
        x <- ask(intActor, Request).mapTo[Int]
        s <- (stringActor ask Request).mapTo[String]
        d <- (doubleActor ? Request).mapTo[Double]
      } yield Result(x, s, d)
    Await.result(f, Timeout(5 seconds).duration)
    println(f.value.get)
  }
}
