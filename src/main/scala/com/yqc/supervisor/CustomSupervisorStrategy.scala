package com.yqc.supervisor

import akka.actor.SupervisorStrategy.{Decider, Restart, Stop}
import akka.actor.{ActorKilledException, DeathPactException, OneForOneStrategy, SupervisorStrategy, SupervisorStrategyConfigurator}
import com.yqc.exception.CustomException

import scala.concurrent.duration._

class CustomSupervisorStrategy extends SupervisorStrategyConfigurator {

  println("init!")

  final val customDecider: Decider = {
    case _: CustomException => Restart
    case _: ActorKilledException => Stop
    case _: DeathPactException => Stop
    case _: Exception => Restart
  }

  final val customStrategy: SupervisorStrategy = {
    OneForOneStrategy(maxNrOfRetries = 2, withinTimeRange = 1 minutes)(customDecider)
  }

  override def create(): SupervisorStrategy = customStrategy
}

