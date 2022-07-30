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
     *  Перемещяем элемен в стопку in и удаляем его из прошлой стопки.
     *  Счетчик стопки в которую мы добавили увеличивается
     *  Из которой взяли уменьшается.
     */
    public T poll() {
        if (out.isEmpty() && in.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            in.push(out.pop());
            countIn++;
            countOut--;
        }
        return in.pop();
    }

    /**
     * помещает значение в конец.
     */
    public void push(T value) {
        in.push(value);
        countIn++;
    }
  }
