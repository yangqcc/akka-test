package com.yqc.base.collection

import java.text.SimpleDateFormat
import java.util.{Calendar, Date, TimeZone}

object CollectionTest {

  def main(args: Array[String]): Unit = {
    val arr: Array[Int] = Array(1, 3, 4, 5)
    val sum: Int = arr.sum
    val arr1 = arr map { x => if (x == 1) sum else x }
    arr1 foreach println

    val cache: Map[String, String] = Map("yang" -> "cds")
    println(cache("yang"))
    println(cache.get("cds").isEmpty)

  }
}

object test {
  def main(args: Array[String]): Unit = {
    val now: Calendar = Calendar.getInstance()
    now.setTimeZone(TimeZone.getTimeZone("GMT+8"))
    val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    println(sdf.format(new Date(now.getTimeInMillis)))
  }
}