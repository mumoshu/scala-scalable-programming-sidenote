package com.github.mumoshu.ssp.java;

public class Concrete implements Abstract<String> {

    private String current = initial();

    @Override
    public String transform(String s) {
        return s + s;
    }

    @Override
    public String initial() {
        return "hi";
    }

    @Override
    public void setCurrent(String s) {
        this.current = s;
    }

    @Override
    public String current() {
        return this.current;
    }
}
