package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    /**
     * out стопка из которой берем
     * in стопка куда перекладывааем, чтобы достать элемент out со дна.
     */
    private final SimpleStack<T> out = new SimpleStack<>();
    private final SimpleStack<T> in = new SimpleStack<>();


    /**
     *  должен возвращать первое значение и удалять его из коллекции.
     *  Перемещяем элемен в стопку и удаляем его из прошлой стопки
     */
    public T poll() {
        if (out == null && in == null) {
            throw new NoSuchElementException();
        } else {
            in.push(out.pop());
        }
        return in.pop();
    }

    /**
     * помещает значение в конец.
     */
    public void push(T value) {
        in.push(value);
    }
  }
