package com.yqc.akka.future

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

/**
  * Created by yangqc on 2017/7/31
  */
object FutureTest2 {

  import scala.concurrent.ExecutionContext.Implicits.global

  val f: Future[List[String]] = Future {
    print("this is future function!")
    List[String]("hello", "this", "is")
  }

  def showName(list: List[String]) = {
    var finalStr = ""
    for (s <- list) {
      finalStr += s
    }
    finalStr
  }

  def computation(): Int = {
    25 + 50
  }

  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global

    val theFuture = Future {
      Thread.sleep(3000)
      computation()
    }

    theFuture.onComplete {
      case Success(result) => println(result)
      case Failure(t) => println(s"Error:${t.getMessage}")
    }

    /**
      * 等待执行结束
      */
    Await.result(theFuture, FiniteDuration(4000, TimeUnit.SECONDS))
  }
}
