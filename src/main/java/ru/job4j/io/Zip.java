package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Архивация проекта - это процесс создания архива путем упаковывания в него файлов.
 */
public class Zip {
    /**
     * -d - directory - папка, которую мы хотим архивировать.
     * -e - exclude - исключить файлы с расширением class.
     * -o - output - во что мы архивируем.
     */
    private static String directory = "d";
    private static String  exclude = "e";
    private static String output = "o";

    /**
     * Считывает ВСЕ файлы из переданного списка в архив.
     * Прходим по списку и читаем объекты Zip
     * Добавляем их в path, который читаем и передаём в sours
     * @param source список куда записываем значения из target
     * @param target файл из которого читаем
     */
    public void packFiles(List<Path> source, File target) {
         try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
             for (Path path : source) {
                 zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                 try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                     zip.write(out.readAllBytes());
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    /**
     * Архивирует один файл
     * Для записи файлов в архив для каждого файла создается объект ZipEntry,
     * в конструктор которого передается имя архивируемого файла.
     * А чтобы добавить каждый объект ZipEntry в архив, применяется метод putNextEntry().
     * Читаем файл source и записываем в него накопленный поток байтов
     * @param sours файл, в который записывается всё что лежало в target
     * @param target файл, из которого берем наши значения
     */
    public void packSingleFile(File sours, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(sours.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(sours))) {
                zip.write(out.readAllBytes());
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
   private static void validate(ArgsName args) {
       File file = new File(args.get(directory));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Проверьте, что путь - это директория, а не файл: %s ", file.getAbsolutePath()));
        }
        if (!args.get(exclude).startsWith(".") && exclude.length() < 2) {
            throw new IllegalArgumentException("Аргумент 'e' должен начинаться с точки!");
        }
        if (args.get(output).endsWith(".zip")) {
            throw new IllegalArgumentException("Аргумент должен иметь расширение .zip");
        }
    }

    /**
     * @param args маассив
     * @throws IOException search требует
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    String.format("Не то колличество переданных параметров: %s", args.length));
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        List<Path> list = Search.search(Paths.get(argsName.get("d")),
                path -> !path.toFile().getName().endsWith(argsName.get("e")));
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        zip.packFiles(list, new File(argsName.get("o")));
    }
}
