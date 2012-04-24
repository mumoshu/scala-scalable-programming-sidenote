package com.github.mumoshu.ssp.java;

public interface Abstract<T> {

    // def transform(t: T): T
    public T transform(T t);

    // val initial: T
    public T initial();

    // var current: T
    public void setCurrent(T t);
    public T current();
}
