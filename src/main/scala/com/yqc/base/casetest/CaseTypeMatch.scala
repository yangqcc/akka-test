package com.yqc.base.casetest

/**
  * Created by yangqc on 2017/7/9.
  *
  * Scala模式匹配之类型匹配
  */
class CaseTypeMatch {

  /**
    * Scala在运行期使用了泛型擦除模式,泛型参数没有保存到运行期
    * 所以运行期不能判断给定的Map对象创建时带了两个Int参数还是其他的什么类型
    *
    * @param x
    * @return
    */
  def isIntIntMap(x: Any) = x match {
    case m: Map[Int, Int] => true
    case _ => false
  }

  /**
    * 在Scala和Java里，数组被特殊处理了，数组的元素类型与数组之保存在一起,因此它可以做模式匹配
    *
    * @param x
    * @return
    */
  def isStringArray(x: Any) = x match {
    case a: Array[String] => "yes"
    case _ => "no"
  }

  /**
    * 这是构造器匹配，而且是变量绑定,简单的写生变量名，一个@符号，以及这个模式，这种写法创造
    * 了变量绑定模式,这种模式的意义在于它能想通常的那样做模式匹配,并且如果匹配成功，则把变量设置
    * 成匹配的对象，就像使用简单的变量模式那样
    *
    * @param expr
    * @return
    */
  def variableBind(expr: Expr): Expr = expr match {
    case UnOp("abs", e@UnOp("abs", _)) => e
    case _ => new Expr {}
  }
}


object CaseTypeMatch {

  def main(args: Array[String]): Unit = {
    val caseTypeMatch = new CaseTypeMatch
    /**
      * 下面都会返回true,因为进行了泛型擦除
      */
    /*    println(caseTypeMatch.isIntIntMap(Map(1 -> 1)))
        println(caseTypeMatch.isIntIntMap(Map("abc" -> "abc")))

        val as = Array("abc", "cds")
        val ai = Array(1, 2, 3)

        println(caseTypeMatch.isStringArray(as))
        println(caseTypeMatch.isStringArray(ai))*/

    println(caseTypeMatch.variableBind(UnOp("abs", UnOp("abs", UnOp("abc", Var("String"))))))
    println(caseTypeMatch.variableBind(UnOp("abs", UnOp("adc", Var("String")))))
  }
}
