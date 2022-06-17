package ru.job4j.serialization.json;

import org.json.JSONPropertyIgnore;

public class B {

    A a;

    @JSONPropertyIgnore
    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
