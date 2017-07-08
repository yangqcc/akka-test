package com.yqc.base.sealedtest

/**
  * Created by yangqc on 2017/6/22.
  */
abstract class Vegetable(val name: String) {

  def showName = print(name)
}

class Apple(name: String, val age: Int) extends Vegetable(name) {

  override def showName: Unit = {
    super.showName
    print("this is Apple!")
  }
}

object Apple {

  def apply(name: String, age: Int): Apple = new Apple(name, age)
}

object TestInherit {
  def main(args: Array[String]): Unit = {
    Apple("apple", 12).showName
  }
}
