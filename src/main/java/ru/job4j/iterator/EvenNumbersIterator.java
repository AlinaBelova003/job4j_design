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
     * Если элемент четный, то индекс приравниваем к элементу и выходим из цикла.
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                index = i;
                break;
            }
        }
        if (index < data.length) {
           return data[index] % 2 == 0;
        }
        return index <  data.length;
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
