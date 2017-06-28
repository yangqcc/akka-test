package com.yqc.implicittest

import java.io.File

/**
  * Created by yangqc on 2017/6/23.
  */

/**
  * 隐式转换函数统一放在一个包里,隐式函数直接引入
  */
object ImplicitFunction2 extends App {

  import com.yqc.implicittest.implicitConversion.ImplicitConversion._

  var x: Int = 35

  val f = new File("C:\\Users\\yangqc\\IdeaProjects\\akka-test\\src\\main\\resource\\file.log").read
}
