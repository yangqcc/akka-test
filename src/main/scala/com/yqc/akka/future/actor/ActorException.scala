package com.yqc.akka.future.actor

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.yqc.akka.future.actor.SimpleActor.ErrorMessage

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

/**
  * To complete the future with an exception you need to send an akka.actor.Status.Failure message to the sender.
  * This is not done automatically when an actor throws an exception while processing a message.
  *
  * Please note that Scala’s Try sub types scala.util.Failure and scala.util.Success are not treated specially,
  * and would complete the ask Future with the given value - only the akka.actor.Status messages are treated specially by the ask pattern.
  * Created by yangqc on 17-10-21
  */
class SimpleActor extends Actor {
  override def receive = {
    case _: String => "this is reply!"
    case em: ErrorMessage => {
      try {
        val result = operation()
        sender() ! result
      } catch {
        case e: Exception =>
          sender() ! akka.actor.Status.Failure(e)
          throw e
      }
    }
  }

  def operation: () => Int = () => {
    val i: Int = 0
    val j: Int = 1
    j / i
  }
}

object SimpleActor {

  case class ErrorMessage(val message: String)

  def main(args: Array[String]): Unit = {
    implicit val timeout = Timeout(5 seconds)
    val system = ActorSystem("actorSystem")
    val actorRef = system.actorOf(Props[SimpleActor], "simpleActor")
    val result: Future[Any] = actorRef ? ErrorMessage("yes,error!")
    Await.result(result, Timeout(5 seconds).duration)
  }
}