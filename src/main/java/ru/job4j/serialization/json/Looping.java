package ru.job4j.serialization.json;

import org.json.JSONObject;
import ru.job4j.serialization.json.taskjson.Animal;
import ru.job4j.serialization.json.taskjson.Tiger;

public class Looping {

    private static class A {
        private B b;

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }

        public String getHello() {
            return "Hello";
        }
    }

    private static class B {
        private A a;

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);

        System.out.println(new JSONObject(b));

        final Tiger tiger = new Tiger(false, 5, "lion", new Animal("Cat"), new String[] {"Bangladesh", "Vietnam", "India"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isFly", tiger.isFly());
        jsonObject.put("age", tiger.getAge());
        jsonObject.put("name", tiger.getName());
        jsonObject.put("type", tiger.getType());
        jsonObject.put("location", tiger.getLocation());
        System.out.println(jsonObject);
        System.out.println(new JSONObject(tiger));
    }

}
