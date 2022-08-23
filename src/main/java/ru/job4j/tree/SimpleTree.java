package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Добавление узла child
     * Создаём новый узел(father), к которому применяется метод прохода по дереву в ширину. Передаём ему узел родителя.
     * Проверяем, что родительский узел не пустой и значение дочернего узла не содержиться в ...
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> father = findBy(parent);
        if (father.isPresent() && findBy(child).isEmpty()) {
            Node<E> kid = new Node<>(child);
            father.get().children.add(kid);
            result = true;
        }
            return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
