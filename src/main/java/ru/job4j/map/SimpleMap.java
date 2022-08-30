package ru.job4j.map;

import java.util.*;

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
     * Сначала рассчитайте индекс по ключу, а затем проверьте ячейку по этому индексу. Если она пустая, то поместите в нее значение.
     * Передвигаем счетчики вперед
     *
     * @return результат, есть ли место в ячейки
     */
    @Override
    public boolean put(K key, V value) {
        if (capacity * LOAD_FACTOR <= count) {
            expand();
        }
        boolean result = false;
        int bucket = Objects.hashCode(key);
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
     * Принимает поисковый ключ, затем по поисковому ключу рассчитываете индекс ячейки, обращаетесь в эту ячейку,
     * извлекаете значение  хранящегося ключа, сравниваете ключи, и если они одинаковые, то метод возвращает значение из ячейки.
     * Если ключи не одинаковые, то метод возвращает null. При проверке ключей надо учесть, что в ячейке может быть ключ null.
     * Метод get() в случае отсутствия значения должен возвращать null, в противном случае само значение.
     * Рассчитываем индекс и вызываем хеш-код у ключа
     * В методах get() и remove() сравнение не-null ключей должно производиться сначала на равенство их хэшкодов, а только затем по equals().
     */
    @Override
    public V get(K key) {
        V rsl = null;
        int index = indexFor(Objects.hashCode(key));
        if (table[index] != null && Objects.hashCode(key) == Objects.hashCode(table[index].key) && Objects.equals(key, table[index].key)) {
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
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry i : table) {
            if (i != null) {
                int newIndex = indexFor(Objects.hashCode(i.key));
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
        int index = indexFor(Objects.hashCode(kay));
        if (table[index] != null && Objects.hashCode(kay) == Objects.hashCode(table[index].key) && Objects.equals(kay, table[index].key)) {
            table[index] = null;
            count--;
            modCount++;
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
                    throw new ConcurrentModificationException();
                }
                while (count1 < capacity && table[count1] == null) {
                    count1++;
                }
                return count1 < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return table[count1++].key;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleMap<?, ?> simpleMap = (SimpleMap<?, ?>) o;
        return capacity == simpleMap.capacity && count == simpleMap.count && modCount == simpleMap.modCount && Arrays.equals(table, simpleMap.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity, count, modCount);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }

    public static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
