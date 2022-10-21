package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;

public class Analysis {
    /**
     * Находит диапозоны, когда сервер не работал
     * @param sours путь к файлу с диапозонами
     * @param target файл с результатоми анализа
     */
    public void unavailable(String sours, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sours))) {
            List<String> list = reader.lines().toList();
            writeTarget(target);
        }

    }

    /**
     * На основе метода validate() записывает подходящие результаты в файл
     */
    public void writeTarget(String target) {
        try (PrintWriter print = new PrintWriter(new FileOutputStream(target))) {
            validate(target);
        }

    }

    /**
     * Здесь нужно написать условия при которых диапозоны будут записаны
     */
    private static void validate(String target) {

    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
