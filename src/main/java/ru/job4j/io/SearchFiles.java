package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Выведение пути к файлу при использование придиката.
 * FileVisitor — это специальный интерфейс, в котором описаны все методы для обхода дерева файлов.
 * Paths — это простой класс с единственным методом get() помогает из переданной строки или URI получить объект типа Path.
 * Path — это переработанный аналог класса File, с помощью которого можно управлять файлами на диске компьютера
 */
public class SearchFiles implements FileVisitor<Path> {
    List<Path> paths = new ArrayList<>();
    private Predicate<Path> conditional;

    SearchFiles(Predicate<Path> conditional) {
        this.conditional = conditional;
    }

    /**
     * @return список найденных путей
     */
    public List<Path> getPath() {
        return paths;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    /**
     * Находит файл, который подходит под предикат и добавляет его в список.
     * @param file которые мы проверяем и добавляем
     * @param attrs ?
     * @return работу метода
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (conditional.test(file)) {
            paths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
