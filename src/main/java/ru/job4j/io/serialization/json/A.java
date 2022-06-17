package ru.job4j.io.serialization.json;

import org.json.JSONObject;

public class A {

    B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);

        System.out.println(new JSONObject(a));
        System.out.println(new JSONObject(b));
    }
}
