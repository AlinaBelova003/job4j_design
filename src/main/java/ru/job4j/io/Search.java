package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
       Path start = Paths.get("C:\\Users\\alina\\OneDrive\\Рабочий стол\\Потоки ввода -вывода");
       search(start, p -> p.toFile().getName().endsWith(".docx")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPath();
    }

}