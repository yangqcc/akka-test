package com.yqc.akka.dispatcher

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.util.Timeout

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

  override def receive: Receive = ???
}

class ControlActor extends Actor {

  implicit val timeout = Timeout(5, TimeUnit.MINUTES)

  override def receive: Receive = {
    case msg: StartCommand =>
      val startTime = System.currentTimeMillis()
      val actors =
    val results=actors.map(actor=>a)
  }

  def createActor(count: Int): List[ActorRef] = {
    val props = Props(classOf[WriterActor]).withDispatcher("")
    .
    (1 to count)
    .
  }
}

object Master {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("DataInitializer")
    val actor = system.actorOf(Props[ControlActor], "controlActor")
    actor ! StartCommand(100)
  }
}