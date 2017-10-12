package com.yqc.akka.future

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}

/**
  * 直接使用Future
  */
object DirectlyUseFuture2 {

  private implicit val ec = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(2))

  def main(args: Array[String]): Unit = {
    val f1 = Future {
      "Hello" + "World"
    }

    val f2 = Future.successful(3)

    //这里的f3的类型是Future[Future[Int]]
    val f3: Future[Future[Int]] = f1 map { x =>
      f2 map { y =>
        x.length * y
      }
    }

    f3 foreach println
  }
}
