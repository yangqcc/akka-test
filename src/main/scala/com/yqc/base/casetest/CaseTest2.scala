package com.yqc.base.casetest

/**
  * Created by yangqc on 2017/7/9.
  *
  * 带case修饰符的称为样本类,这种修饰符可以让Scala编译器自动为你的类添加
  * 一些语法上的便捷设定
  * 1.它会添加与类名一致的工厂方法,Vae("x")来构造Var对象以代替稍长的一些的new Var("x")
  * 2.样本类的参数列表中的所有的参数列表中所有的参数隐式获得val前缀,因此它被当做字段维护
  * 3.编译器为你的类添加了方法toString,hashCode和equals的"自然"实现,因为Scala里的==始终
  * 直接转到equals.
  *
  * 样本类的最大好处就是能够支持模式匹配
  */

/**
  * sealed 关键字让样本类的超类被封闭，封闭类畜类类定义所在的文件之外，不能添加任何新的子类
  */
sealed abstract class Expr

case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

/**
  * 模式匹配:
  * 1.首先它始终以值作为结果
  * 2.Scala的备选项表达式永远不会"掉到"下一个case
  * 3.入股没有模式匹配，MarchError异常会被跑出,意味着必须始终确信所有的情况
  * 都考虑到了，或者至少意味着可以添加一个摩人情况什么事都不做
  */
class CaseTest2 {

  /**
    * 选择器 match {备选项} "=>"符号隔开了模式和表达式
    *
    * @param expr
    * @return
    */
  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e //双重负号
    case BinOp("+", e, Number(0)) => e //加0
    case BinOp("*", e, Number(1)) => e //乘1
    case _ => expr
  }
}
