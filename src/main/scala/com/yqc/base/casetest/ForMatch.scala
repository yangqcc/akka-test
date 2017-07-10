package com.yqc.base.casetest

/**
  * Created by yangqc on 2017/7/10.
  *
  * for表达式的模式
  */
class ForMatch {

  val results = List(Some("apple"), None, Some("orange"))

  def forMatch(): Unit = {
    for (Some(fruit) <- results) println(fruit)
  }
}

object ForMatch {
  def main(args: Array[String]): Unit = {
    new ForMatch().forMatch()
  }
}
