package com.yqc.base.valueclass

/**
  * A value class can only extend universal traits and cannot be extended itself.
  * A universal trait is a trait that extends Any, only has defs as members, and does no initialization.
  * Universal traits allow basic inheritance of methods for value classes, but they incur the overhead of allocation.
  * @param underlying
  */
class Wrapper(val underlying: Int) extends AnyVal
