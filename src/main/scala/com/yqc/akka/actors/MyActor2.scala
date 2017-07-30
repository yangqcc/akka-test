package com.yqc.akka.actors

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, OneForOneStrategy}

import scala.concurrent.duration.{FiniteDuration, MINUTES}

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

  /**
    * fault tolerance(容错 策略)
    *
    * map方法这样写不能编译通过
    * List(2).map( case 2 => "OK" )
    * *
    * 换做花括号就可以了
    * List(2).map{ case 2 => "OK" }
    *
    * 1）scala中函数的小括号，可以用花括号来表示，即foo{xx} 与 foo(xx)是一回事儿。
    * 2）对于只有一个参数的方法，其小括号是可以省略的，map(lambda)可写为 map lambda，即这块{case 2 => "OK"}
    * 连同花括号整体是一个lambda(函数字面量)。
    */
  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = new FiniteDuration(1, MINUTES))({
      case _: ArithmeticException => Resume
      case _: NullPointerException => Restart
      case _: IllegalArgumentException => Stop
      case _: Exception => Escalate
    })
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