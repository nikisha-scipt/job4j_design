package ru.job4j.list.mytest;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MySimpleLinkedList<E> implements MyTestLinkedList<E> {

    private int size;
    private int modeCount;
    private Node<E> firstElement;
    private Node<E> lastElement;

    private static class Node<E> {

        private E data;
        private Node<E> next;
        private Node<E> previous;

        public Node(E data, Node<E> node) {
            this.data = data;
            this.next = node;
        }

        public Node(E data) {
            this.data = data;
        }
    }

    @Override
    public void addFirst(E value) {
        firstElement = new Node<>(value, firstElement);
        size++;
        modeCount++;
    }

    @Override
    public void addLast(E value) {
        Node<E> node = new Node<>(value);
        lastElement.next = node;
        node.previous = lastElement;
        lastElement = node;
        size++;
        modeCount++;
    }

    @Override
    public void insert(E value) {
        Node<E> current = new Node<>(value);
        if (firstElement == null) {
            current.next = null;
            current.previous = null;
            firstElement = current;
            lastElement = current;
        } else {
            lastElement.next = current;
            current.previous = lastElement;
            lastElement = current;
        }
        size++;
        modeCount++;
    }

    @Override
    public E deleteFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> current = firstElement;
        E value = current.data;
        firstElement = current.next;
        current.next = null;
        current.previous = null;
        current.data = null;
        size--;
        modeCount++;
        return value;
    }

    @Override
    public E deleteLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> current = lastElement;
        E value = current.data;
        lastElement = current.previous;
        lastElement.next = null;
        current.previous = null;
        current.next = null;
        current.data = null;
        size--;
        modeCount++;
        return value;
    }

    @Override
    public void remove(E value) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> current = firstElement;
        Node<E> prev = null;
        while (current != null) {
            if (current.data.equals(value)) {
                break;
            }
            prev = current;
            current = current.next;
        }

        if (current == firstElement) {
            deleteFirst();
        }
        assert prev != null;
        assert current != null;
        prev.next = current.next;
        current.next = null;

        size--;
        modeCount++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E getFirst() {
        Node<E> current = firstElement;
        return current.data;
    }

    @Override
    public E getLast() {
        Node<E> current = lastElement;
        return current.data;
    }

    @Override
    public void revers() {
        if (isEmpty() || this.size == 1) {
            throw new NoSuchElementException();
        }
        Node<E> current = firstElement;
        Node<E> prev = null;

        while (current != null) {
            Node<E> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        firstElement = prev;

    }

    @Override
    public boolean contains(E value) {
        Node<E> current = firstElement;
        boolean rsl = false;
        while (current != null) {
            if (value.equals(current.data)) {
                rsl = true;
                break;
            }
            current = current.next;
        }
        return rsl;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");
        Node<E> current = firstElement;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.append(" }").toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> current = firstElement;
            private final int modeCountEx = modeCount;

            @Override
            public boolean hasNext() {
                if (modeCountEx != modeCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = current.data;
                current = current.next;
                return value;
            }
        };
    }

    public static void main(String[] args) {
        MySimpleLinkedList<Integer> list = new MySimpleLinkedList<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        System.out.println(list);
        System.out.println(list.contains(5));
    }
}
