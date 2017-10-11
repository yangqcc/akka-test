package com.yqc.akka.future

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Promise}

class FutureTest4 {
  def main(args: Array[String]): Unit = {
    implicit val ec = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(2))
    val f = Promise.successful("foo")

    // Then shut your ExecutionContext down at some
    // appropriate place in your program/application
    ec.shutdown()
  }
}
