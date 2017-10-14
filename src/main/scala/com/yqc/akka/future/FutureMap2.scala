package com.yqc.akka.future

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author: yangqc
  * @description: map和flatMap的区别
  * @date: 下午10:20 17-10-14
  */
object FutureMap2 {

  private implicit val ec = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())

  def main(args: Array[String]): Unit = {
    val f1 = Future {
      "Hello," + "World!"
    }

    val f2 = Future.successful(3)

    //这里f3的类型是Future[Future[Int]]
    val f3: Future[Future[Int]] = f1 map { x =>
      f2 map { y =>
        x.length * y
      }
    }

    //要使f3的类型为future,使用flatMap方法
    val f4: Future[Int] = f1 flatMap { x =>
      f2 map { y =>
        x.length * y
      }
    }

    f3 foreach println
    f4 foreach println
  }
}
