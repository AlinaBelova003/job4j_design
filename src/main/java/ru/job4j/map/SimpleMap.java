package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * В этом задании вам необходимо реализовать собственную мапу.
 */
public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Метод вставки put() в случае отсутствия места в ячейке должен возвращать false.
     *  Сначала рассчитайте индекс по ключу, а затем проверьте ячейку по этому индексу. Если она пустая, то поместите в нее значение.
     *  Передвигаем счетчики вперед
     * @return результат, есть ли место в ячейки
     */
    @Override
    public boolean put(K key, V value) {
        if (capacity == count) {
            expand();
        }
        boolean result = false;
        int bucket = hash(key.hashCode());
        int index = indexFor(bucket);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    /**
     *  Метод get() в случае отсутствия значения должен возвращать null, в противном случае само значение.
     *  Рассчитываем индекс и вызываем хеш-код у ключа
     *  В методах get() и remove() сравнение не-null ключей должно производиться сначала на равенство их хэшкодов, а только затем по equals().
     */
    @Override
    public V get(K key) {
        V rsl = null;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && key.hashCode() == table[index].key.hashCode() && key.equals(table[index].key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    /**
     * Определение хеша по ключю
     */
    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
         return (capacity - 1) & hash;
    }

    /**
     * Метод расширяться при достижении значения LOAD_FACTOR=0.75
     */
    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        for (MapEntry i : table) {
            if (i != null) {
                int newIndex = indexFor(hash(i.key.hashCode()));
                newTable[newIndex] = i;
            }
        }
        table = newTable;
    }

    /**
     * Метод remove() в случае успешного удаления должен возвращать true, в противном случае false.
     * * @return
     */
    @Override
    public boolean remove(K kay) {
         boolean result = false;
         int index = indexFor(hash(kay.hashCode()));
         if (table[index] != null && kay.hashCode() == table[index].key.hashCode() && kay.equals(table[index].key)) {
             table[index] = null;
             result = true;
         }

        return result;
    }

    /**
     * Итератор должен обладать fail-fast поведением
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            /**
             * Колличество модификаций списка
             */
            private final int expectedModCount = modCount;
            int count1 = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new NoSuchElementException();
                }
                for (int i = 0; i < table.length; i++) {
                    if (table[count1] == null) {
                        count1++;
                    }
                }
                return count1 <= count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[count1].key;
            }
        };
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
