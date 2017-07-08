package com.yqc.base.implicittest


import java.io.File
import scala.io.Source

/**
  * Created by yangqc on 2017/6/23.
  */

package implicitConversion {

  object ImplicitConversion {

    implicit def double2Int(x: Double) = x.toInt

    implicit def file2RichFile(file: File) = new RichFile(file)
  }

}

class RichFile(val file: File) {

  def read = Source.fromFile(file).getLines().mkString
}

object ImplicitFunction extends App {

  /**
    * 引入隐式函数
    */

  import com.yqc.base.implicittest.implicitConversion.ImplicitConversion._


  var x: Int = 3.5

  val f = new File("C:\\Users\\yangqc\\IdeaProjects\\akka-test\\src\\main\\resource\\file.log").read

  println(f)
}


