package com.yqc.akka.future.comprehension

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.util.Timeout

/**
  * @author: yangqc
  * @description:
  * @date: 下午11:31 17-10-14
  */
class Actor1 extends Actor {

  override def receive: Receive = {
    case "hello" => sender() ! "hello,back!"
  }
}

class Actor2 extends Actor {
  override def receive = {
    case "hi" => sender() ! "hi,back!"
  }
}

class Actor3 extends Actor {
  override def receive = {
    case "actor3" => sender() ! "this is actor3!"
  }
}

object Calculate2 {

  import akka.pattern.ask

  import scala.concurrent.duration._

  implicit val timeout = Timeout(5 seconds)

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("actorSystem")
    val actor1: ActorRef = system.actorOf(Props[Actor1], "actor1")
    val actor2: ActorRef = system.actorOf(Props[Actor2], "actor2")
    val actor3: ActorRef = system.actorOf(Props[Actor3], "actor3")
    val f1 = ask(actor1, "message1!")
    val f2 = ask(actor2, "message2!")

    val f3 = for {
      a <- f1.mapTo[Int]
      b <- f2.mapTo[Int]
      c <- ask(actor3, (a + b)).mapTo[Int]
    } yield c

    f3 foreach println
  }
}
