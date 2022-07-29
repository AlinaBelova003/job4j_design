package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinkedList<T> linked = new ForwardLinkedList<>();

    /**
     * Взять элемент из вершины стека и удалить его из коллекции
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Добавить элемент на вершину стека
     */
    public void push(T value) {
         linked.firstAdd(value);
    }
}
