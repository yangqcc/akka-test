package com.yqc.akka.future.callbacks

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Success, Try}

/**
  * 经andThen返回的新Future无论原Future成功或失败都会返回与原Future一模一样的结果。
  * 一旦原Future完成并返回结果，andThen后跟的代码块就会被调用，且新Future将返回与原Future一样的结果，
  * 这确保了多个andThen调用的顺序执行
  */
object FutureAndThen {
  def main(args: Array[String]): Unit = {
    Future {
      "this is future"
    } andThen ({
      case Success(name) => name + "!"
    }) andThen ({
      case Success(name) => name + "***"
    }) andThen ({
      case x: Try[String] => println(x.get)
    })
  }
}
