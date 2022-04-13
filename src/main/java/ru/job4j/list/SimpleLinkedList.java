package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements MyLinkedList<E> {

    private int size;
    private Node<E> firstElement;
    private Node<E> lastElement;
    private int modCount;

    private static class Node<E> {

        protected final E data;
        protected Node<E> next;
        protected Node<E> previous;

        public Node(E data) {
            this.data = data;
        }
    }


    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (firstElement == null) {
            newNode.next = null;
            newNode.previous = null;
            firstElement = newNode;
            lastElement = newNode;
        } else {
            lastElement.next = newNode;
            newNode.previous = lastElement;
            lastElement = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> res = this.firstElement;
        for (int i = 0; i < index; i++) {
            res = res.next;
        }
        return res.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            final int expectedModCount = modCount;
            private Node<E> current = firstElement;
            private Node<E> previous = null;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E nextData = current.data;
                previous = current;
                current = current.next;
                return nextData;
            }
        };
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = firstElement;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SimpleLinkedList<Integer> sm = new SimpleLinkedList<>();
        sm.add(1);
        sm.add(2);
        sm.add(3);
        Iterator<Integer> it = sm.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());

    }
}
