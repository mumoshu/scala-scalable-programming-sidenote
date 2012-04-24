package com.github.mumoshu.ssp.scala.fruit

/**
 * Fruit の誤ったサブクラス定義
 */
abstract class BadApple extends Fruit {
  def v: String /// val vをdef vでオーバーライドすることはNG
  def m: String
}

/*
上記コードは以下のようなコンパイルエラーになる

[error] /Users/mumoshu/Repository/scala-apps/scala-scalable-programming-sidenote/src/main/scala/com/github/mumoshu/ssp/scala/fruit/BadApple.scala:7: overriding value v in class Fruit of type String;
[error]  method v needs to be a stable, immutable value
[error]   def v: String /// val v??def v?ŃI?[?o?[???C?h???邱?Ƃ?NG
[error]       ^

*/
