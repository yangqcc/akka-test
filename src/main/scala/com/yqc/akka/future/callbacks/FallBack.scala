package com.yqc.akka.future.callbacks

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author: yangqc
  * @description:
  * @date: 上午11:03 17-10-14
  */
object FallBack {

  private implicit val ec = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())

  def main(args: Array[String]): Unit = {

    //success会等待结果一定完成
    val future = Future.successful {
      Thread.sleep(2000)
      "this is result!"
    }

    val future2: Future[String] = Future {
      "this is another result!"
    }

    println(future2.value)
    println(future.value)
  }
}
