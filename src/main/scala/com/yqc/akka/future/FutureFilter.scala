package com.yqc.akka.future

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author: yangqc
  * @description: filter的使用
  * @date: 下午10:28 17-10-14
  */
object FutureFilter {

  private implicit val ec = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())

  def main(args: Array[String]): Unit = {
    val future1 = Future.successful(4)
    val future2 = future1.filter(_ % 2 == 0)

    future2 foreach println

    val failedFilter = future1.filter(_ % 2 == 1).recover {
      case m: NoSuchElementException => 0
    }

    failedFilter foreach println
  }
}
