package com.yqc.base.casetest

/**
  * Created by yangqc on 2017/7/10.
  */
class PartialFunctionTest {

  /**
    * 实现一个偏函数
    */
  val inc = new PartialFunction[Any, Int] {

    /**
      * 告知这个偏函数的接收范围,可以是类型也可以是值
      *
      * @param x
      * @return
      */
    override def isDefinedAt(x: Any): Boolean = if (x.isInstanceOf[Int]) true else false

    /**
      * 用来描述对已接收的值如何处理
      *
      * @param v1
      * @return
      */
    override def apply(v1: Any): Int = v1.asInstanceOf[Int] + 1
  }

  /**
    * case实现偏函数
    */
  val inc2: PartialFunction[Any, Int] = {
    case i: Int => i + 1
  }
}

object PartialFunctionTest {
  def main(args: Array[String]): Unit = {
    println(new PartialFunctionTest().inc(21))
  }
}