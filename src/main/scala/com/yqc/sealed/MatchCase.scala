package com.yqc.`sealed`

/**
  * Created by yangqc on 2017/6/22.
  */
sealed trait People

trait Coder {

  val language: String

  def showLanguage: String = language
}

case object American extends People with Coder {
  override val language: String = "English"
}

case object Japanese extends People

case object Chinese extends People

case object Russia extends People

object test {

  def personType(person: People) = {
    person match {
      case Japanese => print("this is japanese")
      case Chinese => print("this is chinese")
      case Russia => print("this is russia")
      case American => print("this is american")
      //      case _ => print("sorry,I am not know")
    }
  }

  def main(args: Array[String]): Unit = {
    personType(Chinese)
  }
}
