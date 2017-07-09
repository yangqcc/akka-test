/*
package com.yqc.base.casetest

/**
  * Created by yangqc on 2017/7/9.
  */
trait FunctionInherit[+A, -B] extends (A => B) {

  val test: Int => String = {
    case name: Int => name.toString
    case _ => "no parameter!"
  }
}

case class MyCase()

class MyFunction extends FunctionInherit[AnyVal, MyCase] {
  override def apply(v1: AnyVal): MyCase = {
    case MyCase => MyCase
    case _ => MyCase
  }
}

object MyFunction {
  def apply: MyFunction = new MyFunction()

  def main(args: Array[String]): Unit = {
    println(new MyFunction())
  }
}*/
