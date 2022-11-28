package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        List<String> log = new ArrayList<>();
        List<String> random = readPhrases();
        random(random);
        Scanner scanner = new Scanner(System.in);
        String answerUser = scanner.nextLine();
        while (!OUT.equals(answerUser)) {
            if (STOP.equals(answerUser)) {
                log.add(answerUser);
                System.out.println(random);
                answerUser = scanner.nextLine();
            } else if (answerUser.contains(CONTINUE)) {
                log.add(answerUser);
                System.out.println(random);
                answerUser = scanner.nextLine();
            }
            log.add(answerUser);
            break;
        }
        saveLog(log);
    }

    /**
     * Генерация рандомной строки для ответа пользователю, если не введено Флаг- слово
     * мы собираемся сгенерировать случайную строку, используя строчные буквы алфавита и заданную длину:
     * leftLimit = буква а
     * rightLimit = z
     */
    private static void random(List<String> randomFrazes) {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        System.out.println(generatedString);
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

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\consolChat.txt",
                "C:\\projects\\job4j_design\\botAnswer.txt");
        cc.run();
    }
}
