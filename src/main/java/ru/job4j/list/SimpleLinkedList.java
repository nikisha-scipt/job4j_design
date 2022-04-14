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

        protected E data;
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
    public E deleteFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> current = firstElement;
        E data = current.data;
        firstElement = current.next;
        current.next = null;
        current.previous = null;
        current.data = null;
        size--;
        modCount++;
        return data;
    }

    public boolean revert() {
        boolean isCheck = !isEmpty() && size != 1;
        Node<E> current = firstElement;
        Node<E> prev = null;

        while (current != null) {
            Node<E> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        firstElement = prev;

        return isCheck;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            final int expectedModCount = modCount;
            private Node<E> current = firstElement;


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
        System.out.println(sm.revert());
        System.out.println(sm);

    }
}
