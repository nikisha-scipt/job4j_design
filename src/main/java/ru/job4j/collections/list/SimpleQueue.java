package ru.job4j.collections.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleQueue<E> implements MyQueue<E> {

    private int size;
    private int modeCount;
    private Node<E> head;
    private Node<E> tail;

    private static class Node<E> {

        private E value;
        private Node<E> next;
        private Node<E> previous;

        public Node(E value) {
            this.value = value;
        }
    }

    @Override
    public void push(E value) {
        Node<E> current = new Node<>(value);
        if (head == null) {
            current.next = null;
            current.previous = null;
            head = current;
            tail = current;
        } else {
            tail.next = current;
            current.previous = tail;
            tail = current;
        }
        size++;
        modeCount++;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> current = head;
        E value = current.value;
        head = current.next;
        current.next = null;
        size--;
        modeCount++;
        return value;
    }

    public boolean remove(E value) {
        Node<E> current = head;
        Node<E> prev = null;

        while (current != null) {
            if (value.equals(current.value)) {
                break;
            }
            prev = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        } else if (current == head) {
            poll();
            return true;
        }

        prev.next = current.next;
        current.next = null;

        size--;
        modeCount++;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.append("]").toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> current = head;
            private final int modeCountIt = modeCount;

            @Override
            public boolean hasNext() {
                if (modeCountIt != modeCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    public static void main(String[] args) {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
