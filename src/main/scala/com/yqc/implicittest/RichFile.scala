package com.yqc.implicittest {


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

    implicit def double2Int(x: Double) = x.toInt

    var x: Int = 3.5

    /**
      * 隐式函数将File转化为RichFile
      *
      * @param file
      * @return
      */
    implicit def file2RichFile(file: File) = new RichFile(file)

    val f = new File("file.log").read

    println(f)
  }

}

