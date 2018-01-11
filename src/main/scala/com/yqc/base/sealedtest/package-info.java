package com.yqc.base.sealedtest;
/**
 * sealed关键字
 * ​sealed关键字可以修饰类和特质（特质）。密封类提供了一种约束：不能在类定义的文件之外定义任何新的子类。然而，这样做有什么用呢？
 * <p>
 * sealed关键字作用:
 * (1)其修饰的trait，class只能在当前文件里面被继承；
 * (2)在检查模式匹配的时候，用sealed修饰目的是让scala知道这些case的所有情况，scala就能够在编译的时候进行检查，
 * 看你写的代码是否有没有漏掉什么没case到，减少编程的错误
 * #如果不要编译器检查，可以去掉警告:
 * def people(p: People) = (p: @unchecked) match {
 *  case American ⇒ println("American person")
 *  case Japanese ⇒ println("Japanese person")
 *  case Chinese ⇒ println("Chinese person")
 * }
 */