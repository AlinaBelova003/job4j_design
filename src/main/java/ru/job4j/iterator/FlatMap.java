package ru.job4j.iterator;


import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * FlatMap для Iterator(метод flatMap позволяет получить из элемента потока другой поток.)
 *
 */
public class FlatMap implements Iterator<Integer> {
    private final Iterator<Iterator<Integer>> data;
    private Iterator<Integer> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<Integer>> data) {
        this.data = data;
    }

    /**
     * Проверяем, что data(итератор, который содержит несколько итераторов внутри) имеется к выдачи.
     * И cursor(итератор, внутри которого располагаются элементы int) не пустой. Если результат удовлетворяющий,
     * то помещяем в поток cursor новый итератор
     * @return Если false, то cursor остаётся в нетронутом состоянии.
     */
    @Override
    public boolean hasNext() {
        while (data.hasNext() && !cursor.hasNext()) {
            cursor = data.next();
        }
        return cursor.hasNext();
    }

    /**
     * Проверка, в случаи если в iterator нет элементов.
     * @return числа нового потока.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}
