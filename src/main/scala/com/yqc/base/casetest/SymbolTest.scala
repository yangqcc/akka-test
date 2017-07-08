package com.yqc.base.casetest

/**
  * Created by yangqc on 2017/7/9.
  *
  * Scala中的=>符号可以看做是创建函数实例的语法糖。
  * 例如：A => T，A,B => T表示一个函数的输入参数类型是“A”，“A,B”，返回值类型是T
  */
class SymbolTest {

  /**
    * 函数 Int=>String,输入类型是Int,返回类型是String
    */
  val f: Int => String = myInt => "The value of myInt is: " + myInt.toString

  val f2: (Int, Int) => String = (x1: Int, x2: Int) => (x1 + x2).toString

}

object SymbolTest {

  def apply: SymbolTest = new SymbolTest()

  def main(args: Array[String]): Unit = {
    println(new SymbolTest().f(2))
  }
}
