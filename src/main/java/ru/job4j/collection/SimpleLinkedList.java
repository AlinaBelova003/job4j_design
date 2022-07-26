package ru.job4j.collection;

import org.w3c.dom.Node;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
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


        public Node(E data, Node<E> next) {
            this.next = next;
            this.data = data;
        }
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
   
     */
    @Override
    public void add(E value) {
        Node<E> newList = new Node<>(value, null);
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

    /**
     * Метод возвращает элемент, расположенный по указанному индексу.
     * Проверяем, что индекс находиться в пределах диапозона size.
     * Элемент(result) находиться в голове списка
     * Проходим до индекса. Двигая голову.
     * @return значение(данные пользователя) хранившиеся в узле
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = head;
        for (int i = 0; i < index; i++) {
            result = head.next;
        }
        return result.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final int expectedModCount = modCount;
            private SimpleLinkedList.Node<E> index = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index != null;
            }

            /**
             * Элемент равен голове списке со всеми данными. Голова движется по списку.
             * @return голова списка
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = index.data;
                index = index.next;
                return element;
            }
        };
    }
}
