package com.yqc.base.convariance

/**
  * scala的逆变(contravariance)和协变(covariance)
  *
  * @param title
  */
class Publication(val title: String)

class Book(title: String) extends Publication(title)

trait GetInfoAction[P >: Book, R <: AnyRef] {
  def apply(book: P): R
}

object Library {

  val books: Set[Book] =
    Set(
      new Book("Programming in Scala"),
      new Book("Walden")
    )

  def printBookList(info: Book => AnyRef): Unit = {
    assert(info.isInstanceOf[Function1[_, _]])
    for (book <- books)
      println(info(book))
  }

  def printBookListByTrait[P >: Book, R <: AnyRef](action: GetInfoAction[P, R]): Unit = {
    for (book <- books)
      println(action(book))
  }
}

object Customer extends App {
  def getTitle(p: Publication): String = p.title

  Library.printBookList(getTitle)

  Library.printBookListByTrait(new GetInfoAction[Publication, String] {
    override def apply(p: Publication): String = p.title
  })
}