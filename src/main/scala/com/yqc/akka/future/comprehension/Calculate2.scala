package com.yqc.akka.future.comprehension

import java.util.concurrent.Executors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.util.Timeout

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author: yangqc
  * @description:
  * @date: 下午11:31 17-10-14
  */
class Actor1 extends Actor {

  override def receive: Receive = {
    case "hello" => sender() ! 1
  }
}

class Actor2 extends Actor {
  override def receive = {
    case "hi" => sender() ! 2
  }
}

class Actor3 extends Actor {
  override def receive = {
    case x: Int => sender() ! x
    case x: String => sender() ! x
  }
}

object Calculate2 {

  import akka.pattern.ask

  import scala.concurrent.duration._

  implicit val timeout = Timeout(5 seconds)
  //该线程池用于执行f3的future
  implicit val ec = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("actorSystem")
    val actor1: ActorRef = system.actorOf(Props[Actor1], "actor1")
    val actor2: ActorRef = system.actorOf(Props[Actor2], "actor2")
    val actor3: ActorRef = system.actorOf(Props[Actor3], "actor3")
    val f1 = ask(actor1, "hello")
    val f2 = ask(actor2, "hi")

    val f3: Future[String] = for {
      a <- f1.mapTo[Int]
      b <- f2.mapTo[Int]
      c <- ask(actor3, (a + b)).mapTo[String]
    } yield c

    println(f3.value)
  }
}
