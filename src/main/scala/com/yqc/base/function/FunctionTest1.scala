package com.yqc.base.function

object FunctionTest1 {

  def findFirstString(arr: Array[String], target: String): Int = {
    def loop(idx: Int): Int = idx match {
      case l if (l >= arr.length) => -1
      case i if (arr(i) == target) => idx
      case _ => loop(idx + 1)
    }

    loop(0)
  }

  /**
    * 高阶函数
    *
    * @param arr
    * @param target
    * @param equ
    * @tparam A
    * @return
    */
  def findFirstA[A](arr: Array[A], target: A)(equ: (A, A) => Boolean): Int = {
    def loop(idx: Int): Int = idx match {
      case l if (l > arr.length) => -1
      case i if (arr(i) == target) => idx
      case _ => loop(idx + 1)
    }

    loop(0)
  }

  def partialApply[A, B, C](a: A, f: (A, B) => C): B => C = (b: B) => f(a, b)

  def addTwoParams(a: Int, b: Int) = a + b //> addTwoParams: (a: Int, b: Int)Int

  def equ[A]: (A, A) => Boolean = (a1: A, a2: A) => a1 == a2

  //组合函数
  def compose[A,B,C](f: B => C, g: A => B): A => C = (a: A) => f(g(a))

  def andThen[A,B,C](f: A => B, g: B => C): A => C = (a: A) => g(f(a))

  def main(args: Array[String]): Unit = {
    println(FunctionTest1.findFirstString(Array("Hello", "My", "World"), "My"))
    println(FunctionTest1.findFirstString(Array("Hello", "My", "World"), "12"))
    println(FunctionTest1.findFirstA(Array[Int](1, 3, 4, 5), 4)(equ))
    println(addTwoParams(2, 5)) //> res59: Int = 7
    println(partialApply(2, addTwoParams)(5))

    //> compose: [A, B, C](f: B => C, g: A => B)A => C
    val fadd = (x: Int) => x + 2                    //> fadd  : Int => Int = <function1>
    val fmul = (y: Int) => y * 5                    //> fmul  : Int => Int = <function1>
    val mulThenAdd = compose(fadd,fmul)             //> mulThenAdd  : Int => Int = <function1>
    mulThenAdd(2)
    println((fadd andThen fmul)(2))
  }
}
