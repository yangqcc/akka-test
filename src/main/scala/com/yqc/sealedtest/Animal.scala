package com.yqc.sealedtest

/**
  * Created by yangqc on 2017/6/22.
  */
sealed trait Animal {

  def sayName: String

  def showAge = print("my age is 12")
}

class Dog(val name: String) extends Animal {

  override def sayName: String = this.name
}

object Dog {

  def apply(name: String): Dog = new Dog(name)

  def main(args: Array[String]): Unit = {
    Dog("chaochao").showAge
  }
}
