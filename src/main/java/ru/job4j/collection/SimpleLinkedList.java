package ru.job4j.collection;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.Objects;


public class SimpleLinkedList<E> implements LinkedList<E> {
    /**
     * Ссылка на голову и хвост списка;
     * Размер и счетчик изменений
     */
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private int modCount;

    /**
     * Класс для отдельного элемента списка.
     * Хранит данные и ссылки на следующий элемент
     * @param <E>
     */
    private static class Node<E> {
        E data;
        Node<E> next;
    }

    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Добавляем элемент в список.
     * Создаеём для пользовательских данных новый элемент(newList) в него будут записываться приходящие данные value
     * Если список пуст, то голова и хвост указывают на новый созданный элемент.
     * Если элемент не пуст, то к хвосту задаём ссылку на новый элемент. И этот элемент становится новым хвостом.
     * Новый элемент всегда находиться в хвосте и увеличивает размер и счетчик.
     *
     * @param value
     */
    @Override
    public void add(E value) {
        Node<E> newList = new Node<>();
        newList.data = value;
        if (isEmpty()) {
            newList = head;
            tail = newList;
        } else {
            tail.next = newList;
        }
        tail = newList;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = null;
        for (int i = 0; i < index; i++) {
            result = head.next;
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
