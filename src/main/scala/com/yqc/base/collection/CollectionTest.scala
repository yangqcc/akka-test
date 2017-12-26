package com.yqc.base.collection

object CollectionTest {

  def main(args: Array[String]): Unit = {
    val arr: Array[Int] = Array(1, 3, 4, 5)
    val sum: Int = arr.sum
    val arr1 = arr map { x => if (x == 1) sum else x }
    arr1 foreach println
  }
}
