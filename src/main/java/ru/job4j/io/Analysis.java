package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Analysis {
    /**
     * Находит диапозоны, когда сервер не работал
     * @param sours  путь к файлу с диапозонами
     * @param target файл с результатоми анализа
     */
    public void unavailable(String sours, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sours))) {
            try (PrintWriter print = new PrintWriter(new FileOutputStream(target))) {
                List<String> list = reader.lines().toList();
                for (String line : list) {
                    boolean status = true;
                    if ((line.startsWith("400") || line.startsWith("500")) && status) {
                        print.write(target);
                        status = false;
                    } else if ((line.startsWith("200") || line.startsWith("300")) && !status) {
                        print.write(target);
                        status = true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("server.log", "target.txt");
    }
}
