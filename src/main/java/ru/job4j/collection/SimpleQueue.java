package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private int countIn = 0;
    private int countOut = 0;
    /**
     * out стопка из которой берем
     * in стопка куда перекладывааем, чтобы достать элемент out со дна.
     */
    private final SimpleStack<T> out = new SimpleStack<>();
    private final SimpleStack<T> in = new SimpleStack<>();


    /**
     *  должен возвращать первое значение и удалять его из коллекции.
     *  Перемещяем элементы из стопки out  в in.
     *  Счетчик стопки в которую мы добавили уменьшается
     *  Из которой взяли увеличивается
     */
    public T poll() {
        if (countIn == 0 && countOut == 0) {
            throw new NoSuchElementException();
        }
            out.push(in.pop());
            countIn--;
            countOut++;
            return out.pop();
    }

    /**
     * помещает значение в конец.
     */
    public void push(T value) {
        in.push(value);
        countIn++;
    }
  }
