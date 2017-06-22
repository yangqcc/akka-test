package com.yqc.akka

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by yangqc on 2017/6/23.
  */
case class Greeting(greet: String)

case class Greet(name: String)

object Hello extends App {

  val system = ActorSystem("actor-demo-scala")
  val hello = system.actorOf(Props[Hello], "hello")

  hello ! Greeting("Hello")
  hello ! Greet("Bob")
  hello ! Greet("Alice")
  hello ! Greeting("Hola")
  hello ! Greet("Alice")

  Thread sleep 1000
  system.terminate()
}

class Hello extends Actor {
  var greeting = ""

  override def receive: Receive = {
    case Greeting(greet) => greeting = greet
    case Greet(name) => println(s"$greeting $name")
  }
}
