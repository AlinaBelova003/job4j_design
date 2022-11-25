package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    /**
     * Флаги с ответами от пользователя
     * path - путь
     * botAnswer - ответы пользователя
     */
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswer;

    public ConsoleChat(String path, String botAnswer) {
        this.path = path;
        this.botAnswer = botAnswer;
    }

    /**
     * Описание общей логики чата
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String answerUser = scanner.toString();
        List<String> log = new ArrayList<>();
        while (!answerUser.contains(OUT)) {
            if (answerUser.contains(STOP)) {
                log.add(answerUser);
                answerUser = scanner.nextLine();
            } else if (answerUser.contains(CONTINUE)) {
                log.add(answerUser);
                answerUser = scanner.nextLine();
            }
            log.add(answerUser);
            break;
        }
        saveLog(log);
    }

    /**
     * Считывает фразы из пришедшего файла с ответами от пользователя
     * @return список с ответами
     */
    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswer))) {
            list = br.lines().toList();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Сохраняет чат в файл с указанной кодировки
     * @param log файл, куда записываются ответы пользователя(стоп, продолжить и тд)
     */
    private void saveLog(List<String> log) {
        try (PrintWriter pr = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pr::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
