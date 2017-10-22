package com.yqc.akka.faulttolerance

import akka.actor.ActorSystem
import akka.testkit.javadsl.TestKit
import com.typesafe.config.ConfigFactory

/**
  *
  * (1)-1 to maxNrOfRetries, and Duration.inf to withinTimeRange,then the child is always restarted without any limit
  * (2)-1 to maxNrOfRetries, and a non-infinite Duration to withinTimeRange maxNrOfRetries is treated as 1
  * (3)a non-negative number to maxNrOfRetries and Duration.inf to withinTimeRange
  * withinTimeRange is treated as infinite duration (i.e.) no matter how long it takes,
  * once the restart count exceeds maxNrOfRetries, the child actor is stopped
  *
  * Created by yangqc on 2017/7/30
  */
class FaultHandlingDocSpec(_system: ActorSystem) extends TestKit(_system) {

  def this() = this(ActorSystem(
    "FaultHandlingDocSpec",
    ConfigFactory.parseString(
      """
      akka {
        loggers = ["akka.testkit.TestEventListener"]
        loglevel = "WARNING"
      }
      """)))

  //  "A supervisor" must "apply the chosen strategy for its child" in {
  // code here
  //  }
}
