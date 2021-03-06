package com.yqc.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
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
class MyActor(val name: String) extends Actor {

  val log = Logging(context.system, this)

  override def receive: Receive = {
    case "test" =>
      log.info("received test")
      sender ! "yes"
    case _ => log.info("received unknown message")
  }

  override def preStart(): Unit = {
    println(s"Start actor ${self}")
    super.preStart()
  }

}

object MyActor {

  def props(name: String): Props = Props(classOf[MyActor], name)
}

/**
  * Props工厂方法
  */
object DemoActor {
  def props(name: String): Props = Props(classOf[DemoActor], name)
}

class DemoActor(val name: String) extends Actor {
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

object CreateActor extends App {

  def createActor(actorName: String, props: Props)(implicit system: ActorSystem): ActorRef = system.actorOf(props, actorName)

  implicit val system = ActorSystem("mySystem")
  //  val parameterActor = system.actorOf(Props(classOf[MyParameterActor], "args"), "parameterActor1")
  //  val demoActor = system.actorOf(DemoActor.props("demoActor"))

  val actor1 = createActor("myActor1", MyActor.props("yes"))
  val actor2 = createActor("myActor2", MyActor.props("yes"))
  val actor3 = createActor("myActor3", MyActor.props("yes"))
  val actor4 = createActor("myActor4", MyActor.props("yes"))

  actor1 ! "test"
/*    override def receive: Receive = {
      case "yes" => println("get comeback")
      case _ => println("yes!")
    }*/
}
