package com.yqc.akka.persistence

import akka.actor.{ActorSystem, Props}
import akka.persistence.{PersistentActor, SnapshotOffer}

case class Cmd(data: String)

case class Evt(data: String)

case class ExampleState(events: List[String] = Nil) {
  def updated(evt: Evt): ExampleState = copy(evt.data :: events)

  def size: Int = events.length

  override def toString: String = events.reverse.toString()
}

class ExamplePersistentActor extends PersistentActor {

  val snapShotInterval = 1000
  var state = ExampleState()

  override def receiveRecover: Receive = {
    case evt: Evt => updateState(evt)
    case SnapshotOffer(_, snapshot: ExampleState) => state = snapshot
  }

  def updateState(event: Evt): Unit = state = state.updated(event)

  override def receiveCommand: Receive = {
    case Cmd(data) =>
      persist(Evt(s"${data}-${numEvents}")) { event =>
        updateState(event)
        context.system.eventStream.publish(event)
        if (lastSequenceNr % snapShotInterval == 0 && lastSequenceNr != 0)
          saveSnapshot(state)
      }
    case "print" => println(state)
  }

  def numEvents =
    state.size

  override def persistenceId: String = "sample-id-1"
}

object PersistentTest {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("actorSystem")
    val actorRef = actorSystem.actorOf(Props[ExamplePersistentActor], "examplePersistentActor")
    for (i <- 0 to 10) {
      actorRef ! Cmd("yangqc" + i)
    }
    actorSystem.terminate()
    actorRef ! "print"
    Thread.sleep(1000)
    val actorRef2 = ActorSystem("actorSystem").actorOf(Props[ExamplePersistentActor], "examplePersistentActor")
    actorRef2 ! "print"
  }
}

