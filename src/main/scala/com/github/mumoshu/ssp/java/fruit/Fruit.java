package com.github.mumoshu.ssp.java.fruit;

public abstract class Fruit {

    // val v: String
    // Javaにはvalとdefに相当する区別が不可能
    public abstract String v();

    // def m: String
    // Javaでは普通のメソッド定義
    public abstract String m();
}
