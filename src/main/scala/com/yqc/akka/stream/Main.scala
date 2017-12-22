package com.yqc.akka.stream

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.{Done, NotUsed}

import scala.concurrent._

/**
  * The Materializer is a factory for stream execution engines, it is the thing that makes streams run—you don’t need to worry
  * about any of the details just now apart from that you need one for calling any of the run methods on a Source.
  * The materializer is picked up implicitly if it is omitted from the run method call arguments, which we will do in the following.
  */
object Main extends App {
  implicit val system = ActorSystem("AkkaSystem")
  implicit val ec = system.dispatcher
  //产生这个对象前必须生成system
  implicit val materializer = ActorMaterializer()


  val source: Source[Int, NotUsed] = Source(1 to 100)
  val done: Future[Done] = source.runForeach(i ⇒ println(i))
  done.onComplete(_ ⇒ system.terminate())
}
