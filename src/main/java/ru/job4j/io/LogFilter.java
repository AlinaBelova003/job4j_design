package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Это наглядный пример использование шаблона декоратор. Один поток оборачивается в другой.
 *
 */
public class LogFilter {
    /**
     * Фильтрует поток по проинципу - предпоследний элемент должен быть 404.
     * Создали коллекцию, в которую будем записывать результат.
     * Обернули reader(для чтения потоков символов) в in(для упрощения чтения из потоков ввода символов и повышения производительности)
     * Проходим, сразу читая каждую строку и собирая всё в новый список.
     * Мы не можем найти предпоследний элемент в коллекции, нам нужен массив элементов.
     * line.split = разделяет строку на подстроки по кретерию "".
     * Данные подстроки собираются методом в массив и представляют собой его возвращаемое значение.
     * Находим предпоследний элемент заканчивающийся на 404 и добавляем этот элемент в ранее созданую коллекцию
     */
    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (FileReader reader = new FileReader(file);
             BufferedReader in = new BufferedReader(reader)) {
            for (String line : in.lines().toList()) {
                String[] array = line.split(" ");
                if ((array[array.length - 2]).contains("404")) {
                  list.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
