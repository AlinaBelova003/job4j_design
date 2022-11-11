package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> value = new HashMap<>();

    /**
     * Получение ключа из словаря
     */
    public String get(String key) {
        if (!value.containsKey(key)) {
            throw new IllegalArgumentException("No element with this key" + key);
        }
        return value.get(key);
    }

    private static void validateLength(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
    }
    private static void validate(String line) {
        if (!line.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain the symbol \"=\"", line));
        }
        if (!line.startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not start with - ", line));
        }
        if (line.startsWith("-=")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s contain kay ", line)
            );
        }
        if (line.indexOf("=") == line.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a value", line));
        }
    }

    /**
     * Принимает массив параметров и разбивает их на пару ключ-значение
     *
     */
    public void parse(String[] args) {
        for (String line : args) {
            validate(line);
            String[] split = line.split("=", 2);
            value.put(split[0].substring(1), split[1]);
        }
    }

    /**
     * Метод принимает массив String[] args, затем передает его на обработку методу parse().
     * В результате обработанные данные помещаются в объект names и их затем можно будет извлечь по запросу names.get()
     */
    public static ArgsName of(String[] args) {
        validateLength(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvs = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvs.get("Xmx"));
        ArgsName argsName = ArgsName.of(args);
    }

}
