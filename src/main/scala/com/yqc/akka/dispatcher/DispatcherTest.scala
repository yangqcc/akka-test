package com.yqc.akka.dispatcher

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
  * Created by yangqc on 2017/7/1.
  */
trait Message

case class InsertCommand(recordCount: Int) extends Message

case class ControlCommand(message: String, startTime: Long) extends Message

case class StartCommand(actorCount: Int) extends Message

case class ExecutionResult(costTime: Long) extends Message

class WriterActor extends Actor {

  override def preStart(): Unit = {
    println(Thread.currentThread().getName)
  }

  override def receive: Receive = {
    case StartCommand(100) => println("this is startCommand!")
  }

  println(context.dispatcher)
}

object Master {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("DataInitializer", ConfigFactory.load().getConfig("MyDispatcherExample"))
    val actor = system.actorOf(Props[WriterActor].withDispatcher("my-dispatcher"), "controlActor")
    actor ! StartCommand(100)
  }
}