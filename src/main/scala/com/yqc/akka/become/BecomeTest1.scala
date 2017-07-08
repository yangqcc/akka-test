package com.yqc.akka.become

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by yangqc on 2017/7/8.
  *
  * 重新取代消息处理器，可以用来实现有限状态机
  */
trait BecomeMessage

class MyBecomeMessage1(val content: String) extends BecomeMessage {
  val name: String = content
}

class MyBecomeMessage2(val content: String) extends BecomeMessage {
  val name: String = content
}

class BecomeTest1 extends Actor {

  import context._

  override def receive: Receive = {
    case message: MyBecomeMessage1 =>
      println(message.name + ", before become!")
      become({
        case message: MyBecomeMessage1 =>
          println(message.name + ", after become!")
        case message: MyBecomeMessage2 =>
          println(message.name + ",before unbecome!")
          unbecome()
      })
    case message: MyBecomeMessage2 =>
      println(message.name + "before become!")
  }
}

object BecomeTest1 extends App {
  val system = ActorSystem("actorSystem")
  val becomeRef = system.actorOf(Props[BecomeTest1], "becomeTest1")
  becomeRef ! new MyBecomeMessage1("message1")
  becomeRef ! new MyBecomeMessage1("message2")
  becomeRef ! new MyBecomeMessage2("message2")
  becomeRef ! new MyBecomeMessage1("message1")
}
