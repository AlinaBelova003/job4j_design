package ru.job4j.collection;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.List;
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
     * Перевернуть список
     * Если список не пуст или в нем содержиться не только один элемент, идем дальше
     * При работе у нас есть три указателя: Предыдущий(head), Текущий(current) и Следующий(next) элемент.
     * Создаём ссылку на текущий узел, который ссылается на предыдуший
     * Последнийй элемент(предыдуший) не должен никуда ссылаться.
     * Идем по списку пока текущий элемент не равен null.
     * Сохраняем ссылку на следующий элемент, который ссылается на предыдуший(последний)
     * Смещяем все указатели на один элемент и создаем обратную цепочку:
     * next <- current <- head
     */
      public boolean revert() {
           if (head != null && head.next != null) {
            Node<T> current = head.next;
            head.next = null;
            while (current != null) {
                Node<T> next = current.next;
                current.next = head;
                head = current;
                current = next;
            }

        }
        return false;
    }

    /**
     * Добавляем новый элемент в начало
     * Голова равняется новым узлом
     */
    public void firstAdd(T value) {
        head = new Node<>(value, head);
    }

    /** Удаление первого элемента
     * Если первого элемента  нет, то метод выбрасывает исключение.
     * Сначала сохраняете значение, которое надо вернуть.
     * Затем сохраняете ссылку на узел, который сейчас голова списка, и который нам надо от этого списка отцепить
     * Затем переводите голову списка на второй узел
     * Доступ к бывшей голове списка возможен только через переменную tmp. Воспользуемся ей и обнулим все находящиеся в этом узле ссылки
     *
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        Node<T> tmp = head;
        head = head.next;
        tmp.value = null;
        tmp.next = null;
        return value;
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
