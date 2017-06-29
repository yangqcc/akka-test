package com.yqc.akka.actors

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

/**
  * Created by yangqc on 2017/6/29.
  * 自定义actor
  * actorOf方法返回的是ActorRef的实例。ActorRef实例是可序列化的，可以支
  * 持序列化以及进程间网络间的传输。上面代码中name是可选的。实践中，Akka建
  * 议最好能为Actor命名，以便于记录日志识别是哪一个Actor。如果给定Name，则其
  * 值不能为空字符串，也不允许以$开头，但可以设置为URL编码字符，例如用%20
  * 来表示空格。如果在同一个父Actor下，name出现重复，会抛出
  *akka.actor.InvalidActorNameException异常。
  */
class MyActor extends Actor {

  val log = Logging(context.system, this)

  override def receive: Receive = {
    case "test" => log.info("received test")
    case _ => log.info("received unknown message")
  }

}

/**
  * Props工厂方法
  */
object DemoActor {
  def props(name: String): Props = Props(classOf[DemoActor], name)
}

class DemoActor extends Actor {
  override def receive: Receive = {
    case "yes" => println("this is yes!")
    case _ => println("this is another")
  }
}

class MyParameterActor(val args: String) extends Actor {
  override def receive: Receive = {
    case "get" => println("this is get!")
    case _ => println("this is other message!")
  }

  /**
    * 创建子actor
    */
  val childActor = context.actorOf(Props[MyActor], "myChild")
}

object CreateActor {
  val system = ActorSystem("mySystem")
  val actor = system.actorOf(Props[MyActor], "myActor")
  val parameterActor = system.actorOf(Props(classOf[MyParameterActor], "args"), "parameterActor")
  val demoActor = system.actorOf(DemoActor.props("demoActor"))
}
