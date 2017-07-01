package com.yqc.akka.actors

import akka.actor.{Actor, ActorSystem, IndirectActorProducer, Props}

/**
  * Created by yangqc on 2017/7/1.
  */
class DependencyInjector(applicationContext: AnyRef, beanName: String) extends IndirectActorProducer {

  override def actorClass = classOf[Actor]

  override def produce = new MyActor1

  def this(beanName: String) = this("", beanName)

  val system = ActorSystem("DependencyInjector")
  val actorRef = system.actorOf(Props(classOf[DependencyInjector], applicationContext, "hello"), "helloBean")
}

object DependencyInjector {

}
