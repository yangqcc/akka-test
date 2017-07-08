package com.yqc.base.casetest

/**
  * Created by yangqc on 2017/7/9.
  *
  * 在Scala中，被“{}”包含的一系列case语句可以被看成是一个函数字面量，
  * 它可以被用在任何普通的函数字面量适用的地方，例如被当做参数传递。
  */
class CaseTest {

  val defaultValue: Option[Int] => Int = {
    case Some(x) => x
    case None => 0
  }

  val Int2String: Int => String = {
    case number: Int => number + ""
  }
}

object CaseTest {

  def apply(): CaseTest = new CaseTest()

  def main(args: Array[String]): Unit = {
    CaseTest().defaultValue(Some(12))
  }
}