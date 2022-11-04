package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
       Path start = Paths.get(args[0]);
       search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    /**
     * Проверка правильной передачи аргументов
     * @param args аргумент запуска
     */
    private static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Не то колличество аргументов");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Проверьте, что файл по этому пути существует %s", file.getAbsolutePath()));
        }

        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Проверьте, что путь - это директория, а не файл %s", file.getAbsoluteFile()));
        }

        if (args[1].length() < 2) {
            throw new IllegalArgumentException("Введите ещё один аргумент");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Неправильно указан путь. Сделайте проверку, что строка начинается с точки");
        }

    }
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPath();
    }

}
