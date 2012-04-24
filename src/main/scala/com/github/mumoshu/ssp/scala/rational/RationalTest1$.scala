package com.github.mumoshu.ssp.scala.rational

object RationalTest1 {

  trait RationalTest {
    val numerArg: Int
    val denomArg: Int
  }

  // RationalTest トレイトをミックスインした無名クラス(anonymous class)のインスタンスを生成する
  new RationalTest {
    val numerArg = 1
    val denomArg = 2
  }
}
