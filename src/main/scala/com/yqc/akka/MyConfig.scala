package com.yqc.akka

import java.io.File

import com.typesafe.config.ConfigFactory

class MyConfig {

}

object ConfigTest {
  def main(args: Array[String]): Unit = {
    val parsedConfig = ConfigFactory.parseFile(new File("src/main/resources/application.conf"))
    val conf = ConfigFactory.load(parsedConfig)
    print(conf.getString("complex-app.xx"))
  }
}
