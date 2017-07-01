package com.yqc.akka.actors

import akka.actor.{Actor, ActorLogging, Props}


/**
  * Created by yangqc on 2017/7/1.
  */
object DemoActor {

  def props(magicNumber: Int): Props = Props(new DemoActor(magicNumber))
}

class DemoActor(magicNumber: Int) extends Actor {
  override def receive: Receive = {
    case x: Int => sender() ! (x + magicNumber)
  }
}

class SomeOtherActor extends Actor {

  //不是线程安全的
  context.actorOf(DemoActor.props(42), "demo")

  override def receive: Receive = ???
}

/**
  * 接收的消息类型放在伴生类里面
  */
object MyActor1 {

  case class Greeting(from: String)

  case object GoodBye

}

class MyActor1 extends Actor with ActorLogging {

  import MyActor1._

  override def receive: Receive = {
    case Greeting(greeter) => log.info(s"I was greeted by $greeter.")
    case GoodBye => log.info("Someone said goodbye")
  }
}