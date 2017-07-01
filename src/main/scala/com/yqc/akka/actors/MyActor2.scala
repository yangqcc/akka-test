package com.yqc.akka.actors

import akka.actor.{Actor, ActorSystem}

/**
  * Created by yangqc on 2017/7/1.
  */
/*class MyActor2 {

  implicit val system = ActorSystem("demo")

  def createActor = {
    actor(new Act {
      become {
        case "hello" => sender ! "hi"
      }
    })
  }
}*/

class HotSwapActor extends Actor {

  import context._

  def angry: Receive = {
    case "foo" => sender ! "I am already angry?"
    case "bar" => become(happy)
  }

  def happy: Receive = {
    case "bar" => sender ! "I am already happy :-)"
    case "foo" => become(angry)
  }

  override def receive: Receive = {
    case "foo" => become(angry)
    case "bar" => become(happy)
  }
}

case object Swap

class Swapper extends Actor {

  import context._

  override def receive: Receive = {
    case Swap =>
      println("Hi")
      become {
        case Swap =>
          println("Ho")
          unbecome() // 重置最近的 'become' (完全为了好玩)
      }
  }
}