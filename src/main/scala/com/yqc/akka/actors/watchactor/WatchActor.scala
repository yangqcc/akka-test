package com.yqc.akka.actors.watchactor

import akka.actor.{Actor, Props, Terminated}

/**
  * Created by yangqc on 17-10-20
  */
class WatchActor extends Actor {

  val child = context.actorOf(Props.empty, "child")
  context.watch(child) //this is only call needed for registration
  var lastSender = context.system.deadLetters

  override def receive = {
    case "kill" =>
      context.stop(child)
      lastSender = sender()
    case Terminated(child) => lastSender ! "finished"
  }
}
