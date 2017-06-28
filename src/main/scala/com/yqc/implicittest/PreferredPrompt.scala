package com.yqc.implicittest

/**
  * Created by yangqc on 2017/6/28.
  */
class PreferredPrompt(val preference: String) {

}

object JoesPrefs {
  implicit val prompt = new PreferredPrompt("Yes, master> ")
}

object Greeter {

  def greet(name: String)(implicit prompt: PreferredPrompt): Unit = {
    print("Welcome , " + name + ". The system is ready.")
    print(prompt.preference)
  }

  def main(args: Array[String]): Unit = {
    val bobsPrompt = new PreferredPrompt("relax> ")
    Greeter.greet("Bob")(bobsPrompt)
  }
}

object TestImplicit {

  import com.yqc.implicittest.JoesPrefs.prompt

  def main(args: Array[String]): Unit = {
    Greeter.greet("Bob")
  }
}
