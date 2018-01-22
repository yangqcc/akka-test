package com.yqc.base.convariance

trait MyFruit[+A] {
  def function[B >: A](b: B)
}

object MyFruit {

  val myFruit: MyFruit[MyAnimal] = new MyFruit[MyDog] {
    override def function[B >: MyDog](b: B): Unit = {
      println(b)
    }
  }

  def function1(name: String): MyFruit[MyAnimal] = new MyFruit[MyAnimal] {
    override def function[B >: MyAnimal](b: B): Unit = {
      println(b.getClass)
    }
  }

  def main(args: Array[String]): Unit = {
    myFruit.function(new MyAnimal("cat"))
  }

}

class MyAnimal(val name: String) {
  def getName: String = name

  override def toString: String = name
}

class MyDog(name: String) extends MyAnimal(name) {
  def dogFunction() = println("this is dog function!")
}
