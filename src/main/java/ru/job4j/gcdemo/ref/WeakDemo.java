package ru.job4j.gcdemo.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeakDemo {

    public static void main(String[] args) throws InterruptedException {
        //example1();
        //example2();
        example3();
    }


    private static void example1() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        WeakReference<Object> weakReference = new WeakReference<>(object);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        System.out.println(weakReference.get());
    }

    private static void example2() throws InterruptedException {
        List<WeakReference<Object>> weakReferenceList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            weakReferenceList.add(new WeakReference<Object>(new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Removed");
                }
            }));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }

    private static void example3() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(object, referenceQueue);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("from link " + weakReference.get());
        System.out.println("from queue " + referenceQueue.poll());
    }
}
