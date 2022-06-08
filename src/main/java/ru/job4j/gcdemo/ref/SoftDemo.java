package ru.job4j.gcdemo.ref;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SoftDemo {

    public static void main(String[] args) throws InterruptedException {
        example1();
        example2();
    }

    private static void example1() throws InterruptedException {
        Object strong = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        SoftReference<Object> softReference = new SoftReference<>(strong);
        strong = null;
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        System.out.println(softReference.get());

    }

    private static void example2() {
        List<SoftReference<Object>> softReferenceList = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++) {
            softReferenceList.add(new SoftReference<Object>(new Object() {
                String value = String.valueOf(System.currentTimeMillis());

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            }));
        }
        System.gc();
        int liveObject = 0;
        for (SoftReference<Object> ref : softReferenceList) {
            Object object = ref.get();
            if (object != null) {
                liveObject++;
            }
        }
        System.out.println(liveObject);
    }

}
