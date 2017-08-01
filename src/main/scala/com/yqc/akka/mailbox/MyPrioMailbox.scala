package com.yqc.akka.mailbox

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}
import akka.dispatch.{PriorityGenerator, UnboundedPriorityMailbox}
import akka.event.{Logging, LoggingAdapter}
import com.typesafe.config.{Config, ConfigFactory}

/**
  * Created by yangqc on 2017/7/2.
  */
class MyPriorityMailbox(settings: ActorSystem.Settings, config: Config) extends UnboundedPriorityMailbox(
  PriorityGenerator {
    case 'high_priority => 0
    case 'low_priority => 2
    case PoisonPill => 3
    //默认为1,在high和low之间
    case otherwise => 1
  }
)

class Logger extends Actor {
  val log: LoggingAdapter = Logging(context.system, this)

  self ! 'low_priority
  self ! 'low_priority
  self ! 'high_priority
  self ! 'pigdog
  self ! 'pigdog2
  self ! 'pigdog3
  self ! 'high_priority
  self ! PoisonPill

  def receive = {
    case x => log.info(x.toString)
  }
}

object MailBoxTest {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("mailBoxSystem", ConfigFactory.load().getConfig("MyMailBoxConfig"))
    val a = system.actorOf(Props(classOf[Logger]).withDispatcher("prio-dispatcher"))
    a ! "this is custom message!"
  }
}