package com.github.mumoshu.ssp.java.fruit;

public abstract class Apple extends Fruit {

    private final String v;

    protected Apple(String v) {
        this.v = v;
    }

    @Override
    public String v() {
        return v;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
