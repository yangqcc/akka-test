package com.yqc.akka.future

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Promise}

/**
  *
  * @author: yangqc
  * @description: promise例子
  * @date: 下午7:16 17-10-14
  */
object PromiseTest {

  private implicit val ec = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())

  def main(args: Array[String]): Unit = {
    val promise = Promise[String]()
    val theFuture = promise.future
    promise.success("hello")
    print(theFuture.value.get)
  }
}
