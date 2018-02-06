package com.yqc.akka.actors.example

import akka.actor.typed._
import akka.actor.typed.scaladsl.Actor
import akka.actor.typed.scaladsl.AskPattern._
import akka.testkit.typed.TestKit

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.Await

sealed trait Command

final case class GetSession(screenName: String, replyTo: ActorRef[SessionEvent]) extends Command

sealed trait SessionEvent

final case class SessionGranted(handle: ActorRef[PostMessage]) extends SessionEvent

final case class SessionDenied(reason: String) extends SessionEvent

final case class MessagePosted(screenName: String, message: String) extends SessionEvent

final case class PostMessage(message: String)

class ChatRoom {

  private final case class PostSessionMessage(screenName: String, message: String)
    extends Command

  val behavior: Behavior[Command] =
    chatRoom(List.empty)

  private def chatRoom(sessions: List[ActorRef[SessionEvent]]): Behavior[Command] =
    Actor.immutable[Command] { (ctx, msg) ⇒
      msg match {
        case GetSession(screenName, client) ⇒
          val wrapper = ctx.spawnAdapter {
            p: PostMessage ⇒ PostSessionMessage(screenName, p.message)
          }
          client ! SessionGranted(wrapper)
          chatRoom(client :: sessions)
        case PostSessionMessage(screenName, message) ⇒
          val mp = MessagePosted(screenName, message)
          sessions foreach (_ ! mp)
          Actor.same
      }
    }
}
