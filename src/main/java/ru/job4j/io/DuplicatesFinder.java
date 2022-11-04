package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        SearchFileProperty searchFiles = new SearchFileProperty();
        Files.walkFileTree(Path.of("C:\\Users\\alina\\OneDrive\\Рабочий стол\\Сортировка"), searchFiles);
        searchFiles.outputFiles();
    }
}
