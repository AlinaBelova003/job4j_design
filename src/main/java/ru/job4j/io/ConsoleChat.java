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
     *
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String answerUser = scanner.toString();
        if (answerUser.contains(OUT)) {
            System.out.println(" при вводе слова «закончить» программа прекращает работу.");
        } else if (answerUser.contains(STOP)) {
            System.out.println("программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.");
        } else if (answerUser.contains(CONTINUE)) {
            System.out.println("если пользователь вводит слово «продолжить», программа снова начинает отвечать.");
        }

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
