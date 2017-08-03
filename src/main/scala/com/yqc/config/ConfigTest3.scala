package com.yqc.config

import com.typesafe.config.{Config, ConfigFactory}

/**
  * Created by yangqc on 2017/8/3
  */
object ConfigTest3 {
  def main(args: Array[String]): Unit = {
    val testConfig: Config = ConfigFactory.load("test.conf")
    //合并配置
    val finalConfig: Config = ConfigFactory.load().withFallback(testConfig)
    println(finalConfig.getString("simple-lib.foo"))
    println(testConfig.getConfig("data-center-west").getString("foo"))
  }
}
