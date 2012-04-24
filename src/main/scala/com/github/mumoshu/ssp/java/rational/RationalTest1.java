package com.github.mumoshu.ssp.java.rational;

public class RationalTest1 {

    interface RationalTest {
        Integer numerArg();
        Integer denomArg();
    }

    public static void run() {
        // Scala: RationalTest トレイトをミックスインした無名クラスのインスタンスを生成する
        // Java: RationalTest インタフェースを実装した無名クラスのインスタンスを生成する
        new RationalTest() {

            @Override
            public Integer numerArg() {
                return 1;
            }

            @Override
            public Integer denomArg() {
                return 2;
            }
        };
    }

}
