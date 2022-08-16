package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Метод вставки put() в случае отсутствия места в ячейке должен возвращать false.
     * @return
     */
    @Override
    public boolean put(K key, V value) {
        return false;
    }

    /**
     *  Метод get() в случае отсутствия значения должен возвращать null, в противном случае само значение.
     *   В методах get() и remove() сравнение не-null ключей должно производиться сначала на равенство их хэшкодов, а только затем по equals().
     */
    @Override
    public V get(K key) {
        return null;
    }

    /**
     * Определение хеша по ключю
     */
    private int hash(int hashCode) {
        return 0;
    }

    private int indexFor(int hash) {
        return 0;
    }

    /**
     * Метод расширяться при достижении значения LOAD_FACTOR=0.75
     */
    private void expand() {
        if (table[capacity] == LOAD_FACTOR) {
            capacity = capacity * 2;
        }

    }

    /**
     * Метод remove() в случае успешного удаления должен возвращать true, в противном случае false.
     * * @return
     */
    @Override
    public boolean remove(K kay) {
        return false;
    }

    /**
     * Итератор должен обладать fail-fast поведением
     * @return
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            /**
             * Колличество модификаций списка
             */
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new NoSuchElementException();
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
               return
            }
        };
        return null;
    }

    public static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
