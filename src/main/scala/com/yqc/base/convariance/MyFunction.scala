package com.yqc.base.convariance

object MyObject {
  def apply(): MyObject = new MyObject()
}

class MyObject

trait MyFunction2[+A, -B] {
  def apply[S >: A, C <: B](a: S): C
}

object MyFunctionTest extends App {
  val function1 = new Function[Long, Boolean] {
    override def apply(v1: Long): Boolean = {
      if (v1 > 100L) true
      else false
    }
  }

  def test(x: Long)(fun: Long => Boolean) = fun(x)

  println(test(123L)(function1))
}
