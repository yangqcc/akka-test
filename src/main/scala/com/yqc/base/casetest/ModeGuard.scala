package com.yqc.base.casetest

/**
  * Created by yangqc on 2017/7/10.
  *
  * 模式守卫
  */
//TODO 模式守卫和模式层叠
class ModeGuard {

  /**
    * 表达式BinOp("+",Var("x"),Var("x"))
    * 转化为BinOp("*",Var("x"),Number(2))
    * 下面会编译错误，因为模式变量紧允许在模式中出现依稀
    *
    * @param e
    * @return
    */
  /* def simplifyAdd(e: Expr) = e match {
     case BinOp("+", x, x) => BinOp("*", x, Number(2))
     case _ => e
   }*/

  /**
    * 模式守卫,模式守卫接在模式之后，开始于if，守卫可以使任意的引用模式中的变量的
    * 布尔表达式，如果存在模式守卫，那么只有在守卫返回true的时候匹配才成功，因此该例子
    * 只会匹配带两个相同操作元的二元操作
    *
    * @param e
    * @return
    */
  def simplifyAdd(e: Expr) = e match {
    case BinOp("+", x, y) if x == y =>
      BinOp("*", x, Number(2))
    case _ => e
  }
}
