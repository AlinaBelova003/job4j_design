package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
       Path start = Paths.get("C:\\projects\\job4j_design\\detatest.txt");
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
            throw new IllegalArgumentException(String.format("Проверьте, что файл по этому пути существует %s", args[0]));

        }

        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Проверьте, что путь - это директория, а не файл %s", args[0]));
        }
        if (!args[1].startsWith("."))  {
            throw new IllegalArgumentException(String.format("Параметр не начитнается с . %s", args[1]));

        }

        if (args[1].length() < 2) {
            throw new IllegalArgumentException(String.format("Расщирение не может состоять менее из двух символов: %s", args[1]));

        }


    }
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPath();
    }

}
