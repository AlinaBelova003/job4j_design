package ru.job4j.generics;

import java.util.*;

public final class MemStores<? extends Base> implements Store<T> {
    /**
     *  Хранить данные мы будем в Map:В этой карте ключом будет являться id нашей модели,
     *  а значением - объект, который мы будем хранить в хранилище, в данном случае это обобщенный тип T.
     */
    private final Map<String, T> storage = new HashMap<>();

    /**
     * метод добавляет в хранилище объект типа T, при этом метод ничего не возвращает
     */
    @Override
    public void add(T model) {

    }

    /**
     * метод выполняет замену объекта по id,
     * на объект который передается в параметрах метода и возвращает true, если замена выполнена успешно;
     * @param id
     * @param model
     */
    @Override
    public boolean replace(String id, T model) {
        return false;
    }

    /**
     *  метод удаляет пару ключ-значение по id, который передается в метод и возвращает true,
     *  если удаление выполнено успешно;
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        return false;
    }

    /**
     *  метод возвращает объект по ключу, который равен id, передаваемый в качестве параметра метода
     *  или возвращает null если по такому ключу в Map еще нет пару ключ-значение.
     */
    @Override
    public T findById(String id) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemStores memStores = (MemStores) o;
        return Objects.equals(storage, memStores.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage);
    }
}
