package com.github.mumoshu.ssp.scala.fruit

/**
 * Fruit の正しいサブクラス定義
 */
abstract class Apple extends Fruit {
  val v: String
  val m: String // def mをval mでオーバーライドすることはOK
}
