package com.yqc.akka

import akka.actor.{Actor, ActorRef, Props}

/**
  * Created by yangqc on 2017/6/22.
  */
sealed trait PiMessage

case object Calculate extends PiMessage

case class Work(start: Int, nrOfElement: Int) extends PiMessage

case class Result(value: Double) extends PiMessage

case class PiApproximation(pi: Double)

class Worker extends Actor {

  override def receive: Receive = {
    case Work(start, nrOfElement) => sender ! Result(calculatePiFor(start, nrOfElement))
  }

  def calculatePiFor(start: Int, nrOfElement: Int): Double = {
    var acc = 0.0
    for (i <- start until (start + nrOfElement))
      acc += 4 * (1 - (i % 2) * 2) / (2 * i + 1)
    acc
  }
}

class Mater(nrOfWorkers: Int, nrOfMessage: Int, nrOfElements: Int, listener: ActorRef) extends Actor {

  var pi: Double = _

  var nrOfResults: Int = _

  val start: Long = System.currentTimeMillis

  val workerRouter = context.actorOf(Props[Worker], name = "workerRouter");

  override def receive: Receive = {
    case Calculate =>
      for (i <- 0 until nrOfMessage)
        workerRouter ! Work(i * nrOfElements, nrOfElements)
    case Result(value) =>
      pi += value
      nrOfResults += 1
      if (nrOfResults == nrOfMessage) {
//        listener ! PiApproximation(pi, duration =)
        context.stop(self)
      }
  }
}

/*
class Listener extends Actor{
  override def receive: Receive = {
    case
  }
}*/
