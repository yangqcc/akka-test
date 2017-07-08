package com.yqc.base.implicittest

/**
  * Created by yangqc on 2017/6/29.
  */
class ImplicitValue {

  def showInteger(implicit value: Int) = println(value)
}

object ImplicitValue {

  implicit val implicitValue: Int = 12

  def apply(): ImplicitValue = new ImplicitValue()
}

object TestImplicitValue {

  import com.yqc.base.implicittest.ImplicitValue._

  def main(args: Array[String]): Unit = {
    ImplicitValue().showInteger
  }
}
