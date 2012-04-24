package com.github.mumoshu.ssp.scala.rational

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
    val x = {
      println("initializing x"); "done"
    }
  }

  /* Q. 以下の(1)から(3)のどこでprintlnされるでしょうか？ */

  // (1)
  Demo
  // (2)
  Demo.x

  // (3)

  object Demo2 {
    lazy val x = {
      println("initializing x"); "done"
    }
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
