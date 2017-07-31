package com.yqc.akka.faulttolerance

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorRef, ActorSystem, OneForOneStrategy, Props, SupervisorStrategy}

import scala.concurrent.duration.{FiniteDuration, MINUTES}

/**
  * Created by yangqc on 2017/7/14.
  */
class Supervisor extends Actor {

  var childRef: ActorRef = null

  override def preStart(): Unit = {
    println("父类开始启动!")
  }

  override def supervisorStrategy: SupervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = new FiniteDuration(1, MINUTES)) {
      case _: ArithmeticException => Resume
      case _: NullPointerException => Restart
      case _: IllegalArgumentException => Stop
      case _: Exception => Escalate
    }

  override def receive: Receive = {
    case p: Props => sender() ! (childRef = context.actorOf(p))
    case "child" => childRef ! "start"
    case "stopChild" => childRef ! "stop"
    case "escalateChild" => childRef ! "escalate"
  }
}

class Child extends Actor {

  var state = 0

  override def preStart(): Unit = {
    println("子类开始启动!")
  }

  override def postStop(): Unit = {
    println("子类开始停止!")
  }

  def throwExceptionFunction: Unit = {
    print("开始运行!")
    throw new NullPointerException("报错!")
  }

  def stopActor = {
    throw new IllegalArgumentException
  }

  def escalateActor = {
    throw new Exception
  }

  override def receive: Receive = {
    case ex: Exception => throw ex
    case x: Int => state = x
    case "start" => throwExceptionFunction
    case "stop" => stopActor
    case "escalate" => escalateActor
    case "get" => sender() ! state
  }
}

object SupervisorTest {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem.apply("mySystem")
    val supervisor = system.actorOf(Props[Supervisor], "supervisor")
    supervisor ! Props[Child]
    supervisor ! Props[Child]
    supervisor ! "child"
  }
}