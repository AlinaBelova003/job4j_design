package ru.job4j.collection;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinkedList<T> implements Iterable<T> {
    private Node<T> head;

    private static class Node<T> {
        T value;
        Node<T> next;

        /**
         * Хранит значение елемента и ссылку на следующий элемент
         */
        public Node(T value, Node<T> next) {
            this.next = next;
            this.value = value;
        }
    }

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail.next = tail;
        }
        tail.next = node;
    }

    /**
     * Если следующая ссылка не равна нулю, то ..
     */
    public void remove() {
        Node<T> newKnot = head;
        if (newKnot != null) {
           head = head.next;

        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            ForwardLinkedList.Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }
}
