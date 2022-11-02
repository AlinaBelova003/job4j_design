package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class SearchFileProperty extends SimpleFileVisitor {
    private Map<FileProperty, List<Path>> map = new HashMap<>();

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
}
