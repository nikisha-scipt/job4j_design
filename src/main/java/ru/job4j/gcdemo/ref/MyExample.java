package ru.job4j.gcdemo.ref;

import java.lang.ref.SoftReference;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class MyExample {

    public static void main(String[] args) throws InterruptedException {
        exampleWeak();
        exampleSoft();
    }

    private static void exampleSoft() throws InterruptedException {
        User user = new User("Test", 20);
        SoftReference<User> softUser = new SoftReference<>(user);
        user = null;
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        User us = null;
        if (softUser.get() == null) {
            System.out.println("User is removed");
        } else {
            us = softUser.get();
        }
        System.out.println(us);

    }

    private static void exampleWeak() throws InterruptedException {
        WeakHashMap<User, String> userInfo = new WeakHashMap<>();
        User user1 = new User("Test1", 20);
        userInfo.put(user1, "User1 test#1");
        User user2 = new User("Test2", 18);
        userInfo.put(user2, "User2 test#2");
        User userDelete = new User("Test1", 20);
        userInfo.remove(userDelete);
        System.gc();
        System.out.println(userInfo.get(user1));
        TimeUnit.SECONDS.sleep(5);
        if (userInfo.get(user1) == null) {
            user1 = new User("NewUser1", 25);
            userInfo.put(user1, "NewUser1 test#1");
            System.out.println("User1 is live");
            System.out.println(userInfo.get(user1));
        } else {
            System.out.println("Still alive: " + userInfo.get(user2));
            System.out.println("User1 deleted");
        }

    }

    private static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return age == user.age && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

    }

}
