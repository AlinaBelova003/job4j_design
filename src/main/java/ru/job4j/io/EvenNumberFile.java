package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * FileInputStream	Поток ввода для чтения из файла
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        /**
         * Создаем поток для чтения, помещяем в него готовый файл.
         * StringBuilder может добавлять данные многих типов, ускоряет работу потока
         * С помощью метода append () мы можем добавлять целые, двойные числа, символы - любой числовой тип.
         * Нам нужна переменная, в которую мы будет сохранять результаты прохода while - read
         * while - когда файл достигает конца, то метод read прекращяет чтение
         * Но при этом при каждом проходе, мы переписываем четные значения элементов из int в char
         */
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuffer text = new StringBuffer();
            int read;
            while ((read = in.read()) != -1) {
                if (read % 2 == 0) {
                    text.append((char) read);
                }
            }
            System.out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
