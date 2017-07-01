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

/**
  * The call to actorOf returns an instance of ActorRef.
  * This is a handle to the actor instance and the only way to interact(互动;相互作用;互相影响) with it.
  *
  * The ActorRef is immutable(不可变的) and has a one to one relationship with the Actor it represents
  *
  * The ActorRef is also serializable and network-aware. This means that you can serialize it,
  * send it over the wire and use it on a remote host and it will still be representing the same Actor on the original node,
  * across the network.(ActorRef 是可序列化的，并且可以通过网络传输，仍然代表之前的节点)
  *
  * Actors are automatically started asynchronously when created.(创建完成便异步开启)
  */
class MyActor1 extends Actor with ActorLogging {

  import MyActor1._

  /**
    * 创建子actor
    */
  val child = context.actorOf(Props[MyActor1], name = "myChild")

  override def receive: Receive = {
    case Greeting(greeter) => log.info(s"I was greeted by $greeter.")
    case GoodBye => log.info("Someone said goodbye")
  }
}