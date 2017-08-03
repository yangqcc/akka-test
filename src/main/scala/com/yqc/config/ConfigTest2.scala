package com.yqc.config

import com.typesafe.config.{Config, ConfigFactory}

/**
  * Created by yangqc on 2017/8/3
  */
class Settings(config: Config) {
  config.checkValid(ConfigFactory.defaultReference(), "simple-lib")

  val foo = config.getString("simple-lib.foo")
  val bar = config.getInt("simple-lib.bar")
}

object Settings {
  def apply(config: Config): Settings = new Settings(config)
}

object ConfigTest2 {
  def main(args: Array[String]): Unit = {
    val settings: Settings = Settings(ConfigFactory.load("test.conf"))
    println(settings.bar)
    println(settings.foo)
  }
}
