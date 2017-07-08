package com.yqc.akka.become

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by yangqc on 2017/7/8.
  *
  * become和unbecome实现替换和还原消息处理器
  */
case object Swap

class MySwapper

class Swapper extends Actor {

  import context._

  def testFunction: MySwapper => String = {
    case myswapper: MySwapper => "!"
  }

  override def receive: Receive = {
    case Swap =>
      println("Hi")
      become({
        case Swap =>
          println("Ho")
          unbecome()
      }, discardOld = false) //push on top instead of replace
  }

}

object SwapperApp extends App {
  val system = ActorSystem("SwapperSystem")
  val swap = system.actorOf(Props[Swapper], name = "swapper")
  swap ! Swap
  swap ! Swap
  swap ! Swap
  swap ! Swap
  swap ! Swap
  swap ! Swap
  swap ! Swap
}
