package com.github.mumoshu.ssp.scala.time

trait AbstractTime {
  var hour: Int
  var minute: Int
}

/*
trait AbstractTime {
  // 抽象var hourは以下のgetter/setterに展開される
  def hout: Int
  def hout_=(x: Int)
  // 抽象var minuteは以下のgetter/setterに展開される
  def minute: Int
  def minute_=(x: Int)
}
*/
