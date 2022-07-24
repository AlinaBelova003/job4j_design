package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];

    }

    public void enlargeContainer() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    /**
     *Метод добавляет указанный элемен value.
     * Если длина массива меньше колличества элементов, то вызываем метод увеличивающий массив
     * Если размеры в нормальном диапозоне, то..
     * @param value
     */
    @Override
    public void add(T value) {
        if (container.length <= size) {
            enlargeContainer(container);
        }
        container[size] = value;
        size++;

    }

    /**
     * Устанавливаем новое значение
     * Проверили валидацию.
     *Сохранили элемент по индексу (чтобы его потом вернуть в return), затем новый элемент положить в эту ячейку.
     */
    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T element = container[index];
        container[index] = newValue;
        return element;
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
     * element - в нём сохраняем елемент по index и возращяем его в return
     * уменьшем счетчик количества элементов в массиве (size) и увеличить счетчик структурных изменений (modCount).
     */
    @Override
    public T remove(int index) {
        T element = container[index];
        Objects.checkIndex(index, size);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        size--;
        modCount++;
        container[container.length - 1] = null;
        return element;
    }

    /**
     * Метод возвращает элемент, расположенный по указанному индексу.
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
           private int expectedModCount = modCount;
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
                return container[size++];
            }
        };
    }
}
