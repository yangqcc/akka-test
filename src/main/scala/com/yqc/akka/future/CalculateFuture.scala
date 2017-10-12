package com.yqc.akka.future

import java.util.concurrent.Executors

import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

object CalculateFuture {

  private implicit val ec = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())

  def main(args: Array[String]): Unit = {

    val calculate1 = Future {
      var sum = 0
      var i = 0
      while (i < 10) {
        sum = sum + sum + 1
        i = i + 1
      }
      Thread.sleep(4000)
      sum
    }

    val calculate2 = Future {
      var sum = 0
      var i = 0
      while (i < sum) {
        sum = sum + 1
        i = i + 1
      }
      sum
    }

    val f3 = Future {
      Await.result(calculate1, Timeout(5 seconds).duration) + Await.result(calculate2, Timeout(3 seconds) duration)
    }

    f3 foreach println
  }
}
