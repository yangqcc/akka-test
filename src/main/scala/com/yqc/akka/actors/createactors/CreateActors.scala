package com.yqc.akka.actors.createactors

import akka.actor.{Actor, ActorSystem, Props}

/**
  * create actors's edge case,all will throw IllegalArgumentException
  * Created by yangqc on 17-10-16
  */
case class MyValueClass(v: Int) extends AnyVal

//constructor type is AnyVal
class ValueActor(value: MyValueClass) extends Actor {
  override def receive = {
    case multiplier: Long => sender() ! (value.v * multiplier)
  }
}

//An actor with default constructor values.
class DefaultValueActor(a: Int, b: Int = 5) extends Actor {
  def receive = {
    case x: Int => sender() ! ((a + x) * b)
  }
}

class DefaultValueActor2(b: Int = 5) extends Actor {
  def receive = {
    case x: Int => sender() ! (x * b)
  }
}

object Test1 {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("actorSystem")

    /**
      * Scala API: create a Props given a class and its constructor arguments.
      * this situation is unsupported!
      */
    //    val valueClassProp: Props = Props(classOf[ValueActor], MyValueClass(5))

    val defaultValueProp1 = Props(classOf[DefaultValueActor], 2) // Unsupported

    val defaultValueProp2 = Props[DefaultValueActor2] // Unsupported
    val defaultValueProp3 = Props(classOf[DefaultValueActor2]) // Unsupported
  }
}
