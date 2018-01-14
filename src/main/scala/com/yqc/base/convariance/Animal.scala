package com.yqc.base.convariance

/**
  * 类型通配符和一般的泛型定义不一样，泛型在类定义时使用，而类型能配符号在使用类时使用
  * 类型通配符是用来限制类的使用的,而逆变和协变是用来定义子类的父类的
  */
class Animal[T, B](t: T, b: B)

class Dog

class Cat extends Dog

class Pat[S <: Dog, T >: Cat](s: S, t: T)

abstract class Test[+A, -S](val a: A) {
  def test[C >: A, B <: S](a: C, b: B): B
}

object Test extends App {
  val animal: Animal[_ <: Any, _ >: Book] = new Animal[Dog, Publication](new Dog, new Publication(""))
  val pat: Pat[_ <: Cat, _ >: Dog] = new Pat[Cat, Dog](new Cat, new Dog)
  //  val pat2: Pat[Dog, Dog] = pat  //泛型参数不一致,不能相互转换

  val test: Test[Dog, Cat] = new Test[Dog, Cat](new Dog) {
    override def test[C >: Dog, B <: Cat](a: C, b: B): B = {
      b
    }
  }
  test.test(new Dog, new Cat)
  println(animal)
  println(pat)
}
