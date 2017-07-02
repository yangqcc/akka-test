package com.yqc.akka.mailbox

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}
import akka.dispatch.{PriorityGenerator, UnboundedPriorityMailbox}
import akka.event.{Logging, LoggingAdapter}
import com.typesafe.config.Config
import com.yqc.akka.MyActor

/**
  * Created by yangqc on 2017/7/2.
  */
class MyPrioMailbox(settings: ActorSystem.Settings, config: Config) extends UnboundedPriorityMailbox(

  PriorityGenerator {
    case "highpriority" => 0
    case "lowpriority" => 2
    case PoisonPill => 3

    //默认为1,在high和low之间
    case otherwise => 1
  }
)

class Logger extends Actor {

  val log: LoggingAdapter = Logging(context.system, this)

  override def receive: Receive = {
    case x => log.info(x.toString)
  }
}

object TestMailBox {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("mailBox")
    val myActory = system.actorOf(Props[MyActor].withMailbox("prio-mailbox"))
  }
}