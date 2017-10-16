package com.yqc.akka.actors.props.valueclass

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.yqc.akka.actors.props.valueclass.ValueClassActor.{Animal, Dog}

/**
  * Created by yangqc on 17-10-16
  */
class Argument(val value: String) extends AnyVal

object Argument {

  def apply(value: String): Argument = new Argument(value)
}

/**
  * constructor argument is value class
  *
  * @param arg
  */
class ValueClassActor(arg: Argument) extends Actor {
  override def receive = {
    case Animal => sender() ! "animal"
    case Dog => sender() ! "dog"
    case _ => sender() ! "nothing"
  }
}

object ValueClassActor {

  //fails at runtime
  def props1(arg: Argument) = Props(classOf[ValueClassActor], arg)

  //ok
  def props2(arg: Argument) = Props(classOf[ValueClassActor], arg.value)

  //ok
  def props3(arg: Argument) = Props(new ValueClassActor(arg))

  case class Animal()

  case class Dog()

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("actorSystem")
    val actorRef: ActorRef = system.actorOf(ValueClassActor.props2(Argument("123")))
    actorRef ! Animal
    println(Argument("this is argument!"))
  }
}