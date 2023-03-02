package ru.job4j.io;

import java.io.*;

/**
 * Классы вывода PrintStream / PrintWriter
 */
public class PrintUsage {

    /**
     * PrintStream данный класс для записи информации в поток вывода
     * В качестве потока вывода используется объект FileOutputStream.
     * в метод println() мы передавали сразу строку.
     * Метод write() принимает массив байтов, поэтому переданную строку превращаем в байты с помощью метода getBytes().
     */
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/print.txt"))) {
            stream.println("Из PrintStream в FileOutputStream");
            stream.write("Новая строка".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
