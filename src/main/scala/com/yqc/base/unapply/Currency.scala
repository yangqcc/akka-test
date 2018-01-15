package com.yqc.base.unapply

/**
  * unapply主要用于模式匹配
  */
class Currency(val value: Double, val unit: String)

object Currency {
  def apply(value: Double, unit: String): Currency = new Currency(value, unit)

  def unapply(currency: Currency): Option[(Double, String)] = {
    if (currency == null) {
      None
    }
    else {
      Some(currency.value, currency.unit)
    }
  }

  def main(args: Array[String]): Unit = {
    def testUnapply(currency: Currency) = currency match {
      case Currency(num, "RMB") => println("RMB: " + num)
      case _ => println("Not RMB!")
    }

    testUnapply(new Currency(12, "RMB"))
  }
}
