package com.yqc.base.casetest

/**
  * Created by yangqc on 2017/7/9.
  */
class CaseType {

  val pi: Double = Math.PI

  /**
    * 变量匹配,小写字母的简单名被当做是模式变量，所有的其他引用被当做是常量
    *
    * @return
    */
  def showVariableMatch(): String = Math.E match {
    case pi => "Strange math? Pi = " + pi
  }

  /**
    * 常量匹配
    *
    * @return
    */
  def showConstantMatch(): String = Math.E match {
    case Math.PI => "strange math? Pi = " + Math.PI
    case _ => "OK"
  }

  /**
    * 构造器模式匹配
    *
    * @param expr
    */
  def showConstructorMarch(expr: Expr) = expr match {
    case BinOp("+", e, Number(0)) => println(s"a deep match and 'e' is ${e}")
    case _ => println("nothing")
  }


  /**
    * 常量模式,任何val或者单例随想都可被当做常量
    *
    * @param x
    * @return
    */
  def describe(x: Any): String = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "something else"
  }

}

object CaseType {

  def apply: CaseType = new CaseType()

  def main(args: Array[String]): Unit = {
    val caseType = new CaseType();
    /*   println(caseType.describe(5))
       println(caseType.describe(Nil))
       println(caseType.showConstantMatch())
       println(caseType.showVariableMatch())*/
    caseType.showConstructorMarch(BinOp("+", Var("21"), Number(0)))
    caseType.showConstructorMarch(BinOp("+", Number(12), Number(0)))
  }
}
