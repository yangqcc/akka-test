package com.yqc.exception

/**
  * Created by yangqc on 2017/7/2.
  */
class Problem(val msg: String) extends Exception

object MyObject {

  def f(i: Int) =
    if (i == 0)
      throw new Problem("error!")
    else 24 / i
}

object TestException {
  def main(args: Array[String]): Unit = {
    try {
      MyObject.f(0)
    } catch {
      case err: Problem => println(s"Failed:${err.getMessage}")
    }
  }
}

trait MyExceptionTrait {

  @throws[Problem]
  def showMessage(message: String): Unit
}

class TraitInherit(val name: String) extends MyExceptionTrait {

  override def showMessage(message: String): Unit = throw new Problem("error!")
}

object TraitInherit {

  def apply(name: String): TraitInherit = new TraitInherit(name)

  def main(args: Array[String]): Unit = {
    try {
      TraitInherit("qicheng").showMessage("this is message")
    } catch {
      case error: Problem => println(error + "this is problem!")
      case _ => println("i don't know!")
    }
  }
}