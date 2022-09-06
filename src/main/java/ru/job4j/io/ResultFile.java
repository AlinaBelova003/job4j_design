package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Класс по работе ввода-вывода с FileOutputStream - Поток вывода для записи в файл.
 * Записывам таблицу умножения в файл
 */
public class ResultFile {

    public static int[][] multiple(int size) {
        int[][] row = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int a = 0; a < size; a++) {
                int math = (i + 1) * (a + 1);
                row[i][a] = math;
            }
        }
        return row;
    }
    public static void main(String[] args) {

        /**
         * Открываем поток для записи, передаем путь к файлу, в котором будет храниться запись
         * Записываем работу метода и в отдельную переменную и проходим по циклу
         * Передаем данные, которые хотим записать и преобразовываем их в массив байтов. т.к это байтовый поток
         * для перехода на новую строку используется System.lineSeparator().
         */
        try (FileOutputStream out = new FileOutputStream("Таблица умножения.txt")) {
            int[][] str = multiple(9);
            for (int[] row : str) {
                out.write(Arrays.toString(row).getBytes());
                out.write(System.lineSeparator().getBytes());
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
