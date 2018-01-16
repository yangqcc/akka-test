package com.yqc.base.unapply

class Car(val name: String, val age: Int)

object Car {
  def apply(name: String, age: Int): Car = new Car(name, age)

  def unapply(car: Car): Option[(String, Int)] = Some(car.name, car.age)
}

object Test extends App {

  def function1(car: Car): Unit = car match {
    case Car("yang", age) => println("age is : " + age)
    case _ => println("nothing")
  }

  function1(Car("Nicholas", 12))
  function1(Car("yang", 12))
}
