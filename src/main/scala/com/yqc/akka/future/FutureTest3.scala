package com.yqc.akka.future

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{Await, Future}

/**
  * Created by yangqc on 2017/8/1
  */
object FutureTest3 {
  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global

    val f1 = Future {
      TimeUnit.SECONDS.sleep(1)
      "f1"
    }

    val f2 = Future {
      TimeUnit.SECONDS.sleep(2)
      "f2"
    }

    val f3 = Future {
      TimeUnit.SECONDS.sleep(3)
      2433
    }

    val f4 = Future.sequence(Seq(f1, f2, f3))

    val f5: Future[(String, String, Int)] = for {
      r2 <- f2
      r3 <- f3
      r1 <- f1
    } yield (r1.take(1), r2.drop(1), r3 + 1)

    val results: Seq[Any] = Await.result(f4, FiniteDuration(4000, TimeUnit.SECONDS))

    val (f1Str, f2Str, f3Int) = Await.result(f5, FiniteDuration(4000, TimeUnit.SECONDS))

    println(s"f1: $f1Str, f2: $f2Str, f3: $f3Int") // 输出：f1: f, f2: 2, f3: 2342

//    println("string".take(1))
    println(results)
  }
}
