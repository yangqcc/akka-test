package com.yqc.config

import com.typesafe.config.{Config, ConfigFactory}

/**
  * 测试config
  * Created by yangqc on 2017/8/2
  */
object ConfigTest1 {
  def main(args: Array[String]): Unit = {
    val config: Config = ConfigFactory.load("router.conf").getConfig("akka.actor.deployment")
    println(config.getConfig("/parent/router1").getInt("nr-of-instances"))
  }
}
