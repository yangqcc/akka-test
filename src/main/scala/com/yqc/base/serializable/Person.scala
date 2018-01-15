package com.yqc.base.serializable

import java.io._

class Person extends Serializable {
  var name: String = "yang"
  var age: Int = 12

  override def toString: String = "name=" + name + ", age=" + age
}

object Serialize {
  def main(args: Array[String]): Unit = {
    val file = new File("person.out")
    val out = new ObjectOutputStream(new FileOutputStream(file))
    val person = new Person
    out.writeObject(person)
    out.close()

    val in = new ObjectInputStream(new FileInputStream(file))
    val newPerson = in.readObject()
    in.close()
    println(newPerson)
  }
}
