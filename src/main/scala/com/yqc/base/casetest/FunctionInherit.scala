/*
package com.yqc.base.casetest

/**
  * Created by yangqc on 2017/7/9.
  */
trait FunctionInherit[+A, -B] extends (A => B) {

  /*  val test: Int => String = {
      case name: Int => name.toString
      case _ => "no parameter!"
    }*/
}

sealed abstract class Animal

case class Dog(name: String) extends Animal

case class Cat(name: String) extends Animal

class MyFunction extends FunctionInherit[Animal, String] {
  override def apply(v1: Animal): String = v1.toString
}

object MyFunction {
  def apply: MyFunction = new MyFunction()

  def main(args: Array[String]): Unit = {
    println(new MyFunction())
  }
}

*/
