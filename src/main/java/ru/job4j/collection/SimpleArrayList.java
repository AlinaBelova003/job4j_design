package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
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
        return (T) container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return false;

            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[size];
            }
        };
    }
}
