package com.github.mumoshu.ssp.scala.fruit

/**
 * val は「一定の値」で、defは「呼び出し毎に結果が変わるかもしれない関数」
 */
abstract class Fruit {
  val v: String
  def m: String
}
