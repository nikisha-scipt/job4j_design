package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleStack<E> implements MyStack<E> {

    private Node<E> head;
    private int size;
    private int modCount;

    private static class Node<E> {

        private E data;
        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }


    @Override
    public void push(E value) {
        head = new Node<>(value, head);
        modCount++;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E value = head.data;
        Node<E> current = head;
        head = current.next;
        current.next = null;
        size--;
        modCount++;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> current = head;
            private int modeCountIterator = modCount;

            @Override
            public boolean hasNext() {
                if (modeCountIterator != modCount) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        SimpleStack<Integer> sm = new SimpleStack<>();
        sm.push(1);
        sm.push(2);
        sm.push(3);
        System.out.println(sm);
        sm.pop();
        System.out.println(sm);
        Iterator<Integer> it = sm.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
    }
}
