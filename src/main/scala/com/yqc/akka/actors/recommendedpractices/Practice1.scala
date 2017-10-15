package com.yqc.akka.actors.recommendedpractices

import akka.actor.{Actor, ActorLogging}

/**
  * 将接收参数的类型定义在伴生类里面,这样就比较容易的知道接收消息的类型
  * Another good practice is to declare what messages an Actor can receive in the companion object of the Actor ,
  * which makes easier to know what it can receive.
  * Created by yangqc on 17-10-16
  */
object MyActor {

  case class Greeting(from: String)

  case object Goodbye

}

class MyActor extends Actor with ActorLogging {

  import MyActor._

  def receive = {
    case Greeting(greeter) => log.info(s"I was greeted by $greeter.")
    case Goodbye => log.info("Someone said goodbye to me.")
  }
}
