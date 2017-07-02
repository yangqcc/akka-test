package com.yqc.akka.mailbox

import akka.actor.{ActorSystem, PoisonPill}
import akka.dispatch.{PriorityGenerator, UnboundedPriorityMailbox}
import com.typesafe.config.Config

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
