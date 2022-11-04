package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class SearchFileProperty extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> map = new HashMap<>();

    /**
     * Метод для чтения файлов и нахождения дубликатов в мап
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!map.containsKey(fileProperty)) {
            map.put(fileProperty, new ArrayList<>());
        } else if (map.containsKey(fileProperty)) {
            map.get(fileProperty).add(file);
        }
        return super.visitFile(file, attrs);
    }

    /**
     * Возвращяет набор сопоставлений "ключ-значение" и если их больше чем один элемент, то выводит на печять
     */
    public void outputFiles() throws IOException {
        for (var entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                entry.getValue().forEach(System.out::println);
            }
        }
    }
}
