package com.yqc.akka.actors.props.dependencyinjection

import akka.actor.{Actor, ActorSystem, IndirectActorProducer, Props}

/**
  * 依赖注入构造actor
  * When using a dependency injection framework, actor beans MUST NOT have singleton scope.
  * Created by yangqc on 17-10-16
  */
class DependencyInjector(applicationContext: AnyRef, beanName: String) extends IndirectActorProducer {

  override def produce() = ??? //new Echo(beanName)

  override def actorClass = classOf[Actor]

  def this(beanName: String) = this("", beanName)
}

object Test {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("actorSystem")
    val actorRef = system.actorOf(Props(classOf[DependencyInjector], "hello"), "helloBean")
  }
}
