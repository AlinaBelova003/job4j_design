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
     * Если первого элемента  нет, то метод выбрасывает исключение.
     * Сохраните ссылку на голову списка в промежуточной переменной tmp.
     * Сохранякм значение головы в переменной tmp1.
     * Затем обнуляем ссылки в первом узле и возвращяем ссылку на ...
     *
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> tmp = head.next;
        T tmp1 = head.value;
        tmp.value = null;
        tmp.next = null;
        return tmp1;
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
