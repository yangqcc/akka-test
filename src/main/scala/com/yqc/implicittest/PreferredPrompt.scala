package com.yqc.implicittest

/**
  * Created by yangqc on 2017/6/28.
  */
class PreferredPrompt(val preference: String)

class PreferredDrink(val preference: String)

object JoesPrefs {

  /**
    * prompt一定要是val类型的,但是如果不作为单一标志符处于作用域内,就不会用于填充隐式参数列表
    */
  implicit val prompt = new PreferredPrompt("Yes, master> ")

  implicit val drink = new PreferredDrink("tea")
}

object Greeter {

  /**
    * implicit应用于全体参数列表，而不是单独的参数
    *
    * @param name
    * @param prompt
    * @param drink
    */
  def greet(name: String)(implicit prompt: PreferredPrompt, drink: PreferredDrink): Unit = {
    println("Welcome , " + name + ". The system is ready.")
    print("But while you work,")
    println("why not enjoy a cup of " + drink.preference)
    println(prompt.preference)
  }

  def main(args: Array[String]): Unit = {
    val bobsPrompt = new PreferredPrompt("relax> ")
    val bobsDrink = new PreferredDrink("tea")
    Greeter.greet("Bob")(bobsPrompt, bobsDrink)
  }
}

object TestImplicit {

  import com.yqc.implicittest.JoesPrefs._

  def main(args: Array[String]): Unit = {
    Greeter.greet("Bob")
  }
}
