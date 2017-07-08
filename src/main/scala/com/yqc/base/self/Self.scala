package com.yqc.base.self

/**
  * Created by yangqc on 2017/7/9.
  *
  * self 不是关键字，是this的别名，具有更强的可读性
  */
class Self {
  self =>
  val tmp = "Scala"

  def foo = self.tmp + this.tmp
}
