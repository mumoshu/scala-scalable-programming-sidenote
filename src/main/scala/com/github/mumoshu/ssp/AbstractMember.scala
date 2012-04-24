package com.github.mumoshu.ssp

/**
 * 抽象クラス。Java と同様、直接インスタンス化することはできないクラス。しかし、これは「抽象型」とは呼ばない。
 */
abstract class AbstractClass

/**
 * トレイト。トレイトはその定義上、具体的なクラスではなく、「抽象的」なものである。しかし、「抽象型」とは呼ばない。
 */
trait Abstract {
  /**
   * 抽象型
   */
  type T

  /**
   * 名前と型は定義しているが、式を定義していない。
   * 「抽象メソッド宣言」
   * def の定義上、呼び出すたびに値が異なる可能性がある。
   *
   * @param x
   * @return
   */
  def transform(x: T): T

  /**
   * 名前と型は定義しているが、値は定義していない。
   * 「抽象val宣言」
   * valの定義上、その実装に「何度呼び出しても同じ値を返す」という制限を課す
   */
  val initial: T

  /**
   * 名前と型は定義しているが、初期値を定義していない。
   */
  var current: T
}

class Concrete extends Abstract {
  type T = String

  /**
   * 具象メソッド定義
   * @param x
   * @return
   */
  def transform(x: String) = x + x

  /**
   * valの具象定義
   */
  val initial = "hi"
  var current = initial
}

