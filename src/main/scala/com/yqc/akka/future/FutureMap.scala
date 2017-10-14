package com.yqc.akka.future

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author: yangqc
  * @description:
  * @date: 下午7:30 17-10-14
  */
object FutureMap {

  private implicit val ec = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())

  def main(args: Array[String]): Unit = {

    val f0 = Future {
      println(Thread.currentThread().getName + ",this is f0!")
    }

    val f1 = Future.successful({
      "this is first result！"
    }) map { x =>
      x + "final result！"
    }

    //返回一个future,交给当前线程执行
    val f2 = Future.successful({
      val i = 0
      //      9 / i
      println(Thread.currentThread().getName)
    })

    println(Thread.currentThread().getName)
    println(f1.value)
    println(f2.value)
  }
}
