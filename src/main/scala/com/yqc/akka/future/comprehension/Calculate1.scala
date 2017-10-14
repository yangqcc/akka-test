package com.yqc.akka.future.comprehension

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author: yangqc
  * @description:
  * @date: 下午10:45 17-10-14
  */
object Calculate1 {

  private implicit val ec = ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())

  def main(args: Array[String]): Unit = {
    val f = for {
      a <- Future(10 / 2) //a=5
      b <- Future(a + 1) //b=6
      c <- Future(a - 1) //c=4
      if c > 3 //Future.filter
    } yield b * c

    // Note that the execution of futures a, b, and c
    // are not done in parallel(并行).

    f foreach println
  }
}
