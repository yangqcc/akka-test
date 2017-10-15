package com.yqc.akka.actors.recommendedpractices

import akka.actor.{Actor, Props}

/**
  * 产生Props的方式最好定义在该Actor的伴生对象里面,如果定义在其它类里面,则可能会不安全
  * Created by yangqc on 17-10-16
  */
object DemoActor {
  /**
    * Create Props for an actor of this type.
    *
    * @param magicNumber The magic number to be passed to this actor’s constructor.
    * @return a Props for creating this actor, which can then be further configured
    *         (e.g. calling `.withDispatcher()` on it)
    */
  def props(magicNumber: Int): Props = Props(new DemoActor(magicNumber))
}

class DemoActor(magicNumber: Int) extends Actor {
  def receive = {
    case x: Int => sender() ! (x + magicNumber)
  }
}

class SomeOtherActor extends Actor {
  // Props(new DemoActor(42)) would not be safe
  context.actorOf(DemoActor.props(42), "demo")

  // ...
  override def receive = ???
}