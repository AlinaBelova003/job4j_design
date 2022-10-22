package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Analysis {
    /**
     * Находит диапозоны, когда сервер не работал
     *
     * @param sours  путь к файлу с диапозонами
     * @param target файл с результатоми анализа
     */
    public void unavailable(String sours, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sours))) {
            List<String> list = reader.lines().toList();
            validate(target, list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Здесь нужно написать условия при которых диапозоны будут записаны
     */
    private static void validate(String target, List<String> list) {
        try (PrintWriter print = new PrintWriter(new FileOutputStream(target))) {
            boolean status = true;
            for (String line : list) {
                if (line.startsWith("400") || line.startsWith("500") && status == true) {
                    line = target;
                    print.write(target);
                    status = false;
                } else if (line.startsWith("200") || line.startsWith("300") && status == false) {
                  line = target;
                  print.write(target);
                  status = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
