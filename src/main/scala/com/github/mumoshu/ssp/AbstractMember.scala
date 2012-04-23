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

abstract class Fruit {
  val v: String
  def m: String
}

/**
 * Fruit の正しいサブクラス定義
 */
abstract class Apple extends Fruit {
  val v: String
  val m: String // def mをval mでオーバーライドすることはOK
}

/**
 * Fruit の誤ったサブクラス定義
 */
abstract class BadApple extends Fruit {
  def v: String /// val vをdef vでオーバーライドすることはNG
  def m: String
}

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

object RationalTest2 {

  trait RationalTrait {
    val numerArg: Int
    val denomArg: Int
    require(denomArg != 0)
    private val g = gcd(numerArg, denomArg)
    val numer = numerArg / g
    val denom = denomArg / g
    private def gcd(a: Int, b: Int): Int =
      if (b == 0) a else gcd(b, a % b)
    override def toString = numer + "/" + denom
  }

  val x = 2

  // IllegalArgumentException: requirement failed
  new RationalTrait {
    val numerArg = 1 * x
    val denomArg = 2 * x
  }

  // OK
  new {
    val numerArg = 1 * x
    val denomArg = 2 * x
  } with RationalTrait

  // OK
  object twoThirds extends {
    val numerArg = 2
    val denomArg = 3
  } with RationalTrait

  // OK
  // nやdはクラスパラメータという。
  class RationalClass(n: Int, d: Int) extends {
    val numerArg = n // スーパートレイとの初期化にnやdのようなクラスパラメータを使うのは一般的
    val denomArg = d
  } with RationalTrait {
    def +(that: RationalClass) = new RationalClass(
      numerArg * that.denom + that.numer * denom,
      denom * that.denom
    )
  }
  // NG
  /*
  new { //$iwという無名クラスの定義
    val numerArg = 1
    val denomArg = this.numerArg * 2
  } extends RationalTrait //$iwという無名クラスはRationalTraitを実装する
  // error: value numerArg is not a member of object $iw
   */

  object Demo {
    val x = { println("initializing x"); "done" }
  }

  /* Q. 以下の(1)から(3)のどこでprintlnされるでしょうか？ */

  // (1)
  Demo
  // (2)
  Demo.x
  // (3)

  object Demo2 {
    lazy val x = { println("initializing x"); "done" }
  }

  /* Q. 以下の(1)から(3)のどこでprintlnされるでしょうか？ */

  // (1)
  Demo2
  // (2)
  Demo2.x
  // (3)

  trait LazyRationalTrait {
    val numerArg: Int
    val denomArg: Int
    lazy val numer = numerArg / g
    lazy val denom = denomArg / g
    override def toString = numer + "/" + denom
    private lazy val g = {
      require(denomArg != 0)
      gcd(numerArg, denomArg)
    }
    // greatest common divisor ユークリッドの互除法
    private def gcd(a: Int, b: Int): Int =
      if (b == 0) a else gcd(b, a % b)
  }

  val y = 2

  // OK
  val lazyRational = new LazyRationalTrait {
    val numerArg = 1 * y
    val denomArg = 2 * y
  }

  lazyRational.toString

  // 1. LazyRationalTrait　の初期化コードが実行される。初期化コードはない。(具象val定義や具象var定義がありませんね？)
  // 2. new によって定義された無名サブクラスの基本コンストラクタが実行される。これによりnumerArg = 2、denomArg = 4で初期化される。
  // 3. toString -> numer
  //    numer -> g (lazy val gへの初回アクセスのため、gが初期化される)
  //    toString -> denom
  //    denom -> g (gは既に初期化されている)
  // 4. toStringの結果が生成される。

}
