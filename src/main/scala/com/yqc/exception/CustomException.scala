package com.yqc.exception

import akka.actor.ActorRef

class CustomException protected(actor: ActorRef, message: String, cause: Throwable) extends RuntimeException(message, cause) {
  def getActor: ActorRef = actor
}

object CustomException {
  private def enrichedMessage(actor: ActorRef, message: String) =
    if (actor == null) message else s"${actor.path}: $message"

  def apply(actor: ActorRef, message: String, cause: Throwable = null): CustomException =
    new CustomException(actor, message, cause)

  def apply(message: String): CustomException = new CustomException(null, message, null)

  def unapply(ex: CustomException): Option[(ActorRef, String, Throwable)] = Some((ex.getActor, ex.getMessage, ex.getCause))
}