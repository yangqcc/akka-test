package com.yqc.akka.future

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{Await, Future}

/**
  * Created by yangqc on 2017/8/1
  */
object FutureException {
  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val f1 = Future(6 / 0) recover { case e: ArithmeticException => 0 }
    val f2 = Future(6 / 0) recover { case e: ArithmeticException => 0 }
    val f3 = Future((() => 6 / 2) ()) recover { case e: ArithmeticException => 0 }

    val f4 = Future.sequence(Seq(f1, f2, f3))

    val f5: Future[(Int, Int, Int)] = for {
      r1 <- f1
      r2 <- f2
      r3 <- f3
    } yield (r1, r2, r3)

    val f6: Seq[Int] = Await.result(f4, FiniteDuration(4000, TimeUnit.SECONDS))

    val a: () => Int = () => 6 / 2
    println(f6)
    println(a() + "**")
  }
}
