package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];

    }

    @Override
    public void add(T value) {

    }

    @Override
    public T set(int index, T newValue) {
        return null;
    }

    /**
     * Начиная с Java 9, мы можем проверить, находится ли индекс в пределах указанного диапазона, используя функцию Objects.checkIndex().
     * Он возвращает индекс, если он находится в пределах диапазона, и выдает IndexOutOfBoundsException если индекс выходит за пределы.
     * метод System.arraycopy() для копирования элементов из одного массива в другой.принимает несколько параметров:
     * Первым параметром является массив-источник.
     * Вторым параметром является позиция начала нового массива.
     * Третий параметр — массив куда копируется.
     * Четвертый параметр - начиная с какого индексв.
     * Пятый - количество элементов, которые будут скопированы.
     * Последняя строчка - для прекращения утечки памяти. Последний элемент в массиве null
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return false;

            }

            @Override
            public T next() {
                return null;
            }
        };
    }
}
