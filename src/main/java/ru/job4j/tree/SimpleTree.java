package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Добавление узла child
     * Создаём новый узел(father),хранимый значение parent, к которому применяется метод прохода по дереву в ширину. Передаём ему узел элемент,который надо найти.
     * Проверяем, что родительский узел существует и значение дочернего узла не содержиться в списке, по которому идём в ширину
     * Создаём узел kid, который будет содержать значение child
     * Ищем узел, от которого добавляем дочерний узел в коллекцию
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
        Predicate<Node<E>>  conditional = x -> x.value.equals(value);
        return findByPredicate(conditional);
    }

    /**
     * Метод проверяет,что дерево бинарное
     * @return  если пустое значение - то дерево бинарное, если нет - то дерево небинарное
     */
    public boolean isBinary() {
        Predicate<Node<E>> conditional = x -> x.children.size() > 2;
        return findByPredicate(conditional).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
