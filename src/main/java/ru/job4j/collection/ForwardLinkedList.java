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
     * Добавляем новый элемент в начало
     * Создаем новый узел.
     * Если список пуст, то привязываем ссылки начала и конца на новый элемент
     * Иначе новый элемент теперь ссылается на "бывший" первый
     * Перезначаем указатель head на наш новый узел.
     */
    public void firstAdd(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        } else {
            node.next = head;
            head = node;
        }

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
        tmp = head;
        head = head.next;
        tmp.value = null;
        tmp.next = null;
        return tmp;
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
