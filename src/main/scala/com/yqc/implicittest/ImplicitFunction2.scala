package com.yqc.implicittest

/**
  * Created by yangqc on 2017/7/6.
  */
class ImpliecitFunction2 {
  def maxListImpParam[T](elements: List[T])(implicit orderer: T => Ordered[T]): T =
    elements match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxListImpParam(rest)(orderer)
        if (orderer(x) > maxRest) x
        else maxRest
    }
}
