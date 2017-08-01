package com.yqc.akka.faulttolerance

/**
  * Created by yangqc on 2017/7/30
  */
//TODO 运行不起来，难道是版本的问题?
/*
class FaultHandlingDocSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender {

  def this() = this(ActorSystem(
    "FaultHandlingDocSpec",
    ConfigFactory.parseString(
      """
      akka {
        loggers = ["akka.testkit.TestEventListener"]
        loglevel = "WARNING"
      }
      """)))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A supervisor" must "apply the chosen strategy for its child" in {
    // code here
  }
}
*/
