package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class SearchFileProperty extends SimpleFileVisitor {
    private List<String> list = new ArrayList<>();
    private Map<Long, String> map = new HashMap<>();

    @Override
        public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
            FileProperty fileProperty = new FileProperty();
            if (!list.contains(fileProperty)) {
                map.put(fileProperty, new ArrayList<>());
            } else if (list.contains(fileProperty)) {
                list.add("Как добавить путь?");
            }
            return super.visitFile(file, attrs);
        }
}
