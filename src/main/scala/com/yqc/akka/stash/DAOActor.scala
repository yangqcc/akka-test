package com.yqc.akka.stash

import akka.actor.{Actor, ActorLogging, ActorSystem, Props, Stash}

/**
  * 由于actor没有达到某个状态.可以使用stash()将消息缓存起来
  */
sealed trait DBStates

case object Connected extends DBStates

case object DisConnected extends DBStates

sealed trait DBOperations

case class DBWrite(sql: String) extends DBOperations

case class DBRead(sql: String) extends DBOperations

object DAOActor {
  def props = Props[DAOActor]
}

class DAOActor extends Actor with ActorLogging with Stash {
  override def receive: Receive = disconnected

  def disconnected: Receive = {
    case Connected =>
      log.info("Login to DB.")
      context.become(connected)
      //使用unstashAll,释放出所有缓存的消息
      unstashAll()
    case _ => stash()
  }

  def connected: Receive = {
    case DisConnected =>
      log.info("Logoff from DB.")
      context.unbecome()
      unstashAll()
    case DBWrite(sql) =>
      log.info(s"Writing to DB:$sql")
    case DBRead(sql) =>
      log.info(s"Reading from DB:$sql")
    case _ => stash()
  }
}

object BecomeDB extends App {
  val dbSystem = ActorSystem("dbSystem")
  val dbActor = dbSystem.actorOf(DAOActor.props, "dbActor")

  dbActor ! DBWrite("Update table x")
  dbActor ! Connected
  dbActor ! Connected
  dbActor ! DBRead("Select from table x")
  dbActor ! DisConnected
  dbActor ! DBRead("Select from table x")

  scala.io.StdIn.readLine()
  dbSystem.terminate()
}
