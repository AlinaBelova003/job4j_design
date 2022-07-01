package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Создать итератор четные числа
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * Проверяет массив элементов на четность
     * начиная с index, проходим массив, пока не найдем четный элемент.
     * Когда он найден, то запоминаем его индекс и выходим из цикла.
     * @return проверяем, что индекс не вышел за пределы массива и что элемент по этому индексу четный.
     */
    @Override
    public boolean hasNext() {
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                index = i;
                break;
            }
        }
        return index <  data.length && data[index] % 2 == 0;
    }

    /**
     * Метод проверяет исключение
     * @return возвращяет только четные числа
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
