package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> value = new HashMap<>();

    /**
     * Получение ключа из словаря
     */
    public String get(String key) {
        return value.get(key);
    }

    private static void validateLength(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
    }
    private static void validateSymbol(String args) {
        if (args.startsWith("-") && args.contains("=")) {
            throw new IllegalArgumentException("Don't symbol - or =");
        }
    }
    private static void validateKeyValue(String args) {
        String[] line = args.split("=", 2);
        if (line[0].isBlank() && line[1].isEmpty()) {
            throw new IllegalArgumentException("Don't key or value");
        }
    }

    /**
     * Принимает массив параметров и разбивает их на пару ключ-значение
     *
     */
    public void parse(String[] args) {
        validateLength(args);
        validateSymbol(args);
        validateKeyValue(args);
    }

    /**
     * Метод принимает массив String[] args, затем передает его на обработку методу parse().
     * В результате обработанные данные помещаются в объект names и их затем можно будет извлечь по запросу names.get()
     */
    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvs = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvs.get("Xmx"));
    }

}
