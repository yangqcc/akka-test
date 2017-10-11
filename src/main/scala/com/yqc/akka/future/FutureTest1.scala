package com.yqc.akka.future

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.concurrent.Future

/**
  * actor中使用future
  * Created by yangqc on 2017/7/31
  */
class A extends Actor {

  //  val f = Future("this is future!")

  override def receive: Receive = {
    case "hello" => sender() ! "back!"
  }
}

object FutureTest1 {

  import akka.pattern.ask
  import akka.util.Timeout

  import scala.concurrent.duration._

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("actorSystem")
    val aRef: ActorRef = system.actorOf(Props[A], "aRef")

    implicit val timeout = Timeout(5 seconds)
    //    val future = aRef ? "hello"
    //下面这个会阻塞,直到返回
    //    val result = Await.result(future, Timeout(10 seconds).duration).asInstanceOf[String]
    val future: Future[String] = ask(aRef, "hello").mapTo[String]
    print(future.value.get)
  }
}
