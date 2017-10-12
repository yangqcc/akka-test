package com.yqc.akka.future

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}

/**
  * 直接使用future,不使用actor
  */
object DirectlyUseFuture1 {

  private implicit val ec = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(2))

  def main(args: Array[String]): Unit = {
    val future = Future {
      "Hello" + ",World"
    }
    future.foreach((x: String) => print(x + "!"))
    future foreach print
  }
}
