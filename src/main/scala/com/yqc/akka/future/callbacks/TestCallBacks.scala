package com.yqc.akka.future.callbacks

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object TestCallBacks {
  def main(args: Array[String]): Unit = {
    Future[String] {
      Thread.sleep(1000)
      "this is future test"
    }.onComplete({
      case Success(name) => {
        println("this is success!")
        println(name)
      }
      case Failure(ex) => println(ex.getMessage)
    })

    Future[String] {
      throw new RuntimeException("error!")
    }.mapTo[String].onComplete({
      case Success(name) => println(name)
      case Failure(ex) => println(ex.getMessage)
    })
  }

}
