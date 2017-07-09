package com.yqc.base.casetest

/**
  * Created by yangqc on 2017/7/9.
  *
  * 在Scala中，被“{}”包含的一系列case语句可以被看成是一个函数字面量，
  * 它可以被用在任何普通的函数字面量适用的地方，例如被当做参数传递。
  */
class CaseTest1 {

  val defaultValue: Option[Int] => Int = {
    case Some(x) => x
    case None => 0
  }

  val Int2String: Int => String = {
    case 12 => "12"
    case number: Int => number + ""
  }
}

object CaseTest1 {

  def apply(): CaseTest1 = new CaseTest1()

  def main(args: Array[String]): Unit = {
    CaseTest1().defaultValue(Some(12))
  }
}