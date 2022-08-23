package ru.job4j.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Создаём элементарную структуру дерева
 * Класс Node описывает узел дерева. Узел содержит хранимое значение(value) и ссылки на дочерние узлы.
 * @param <E>
 */
public interface Tree<E> {
    boolean add(E parent, E child);
    Optional<Node<E>> findBy(E value);

    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }

}
