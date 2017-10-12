package com.yqc.akka.future

import java.util.concurrent.Executors

import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

/**
  * Future的Map方法的描述
  *
  * Creates a new future by applying a function to the successful result of
  * this future. If this future is completed with an exception then the new
  * future will also contain this exception.
  */
object DirectlyUseFuture3 {

  private implicit val ec = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())

  def main(args: Array[String]): Unit = {

    val f1 = Future {
      val i = 1
      val j = 2
      val result = i + j
      result
    }

    val f3: Future[String] = f1 flatMap { x =>
      Future(x + ",yes!")
    }

    val f2: Future[Future[String]] = f1 map { x =>
      f1 map { y =>
        y + ",yes," + x
      }
    }

    Await.result(f2, Timeout(5 seconds).duration)

    f2 foreach println
  }
}
