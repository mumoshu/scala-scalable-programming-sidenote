package com.github.mumoshu.ssp.scala.fruit

/**
 * Fruit の誤ったサブクラス定義
 */
abstract class BadApple extends Fruit {
  def v: String /// val vをdef vでオーバーライドすることはNG
  def m: String
}
