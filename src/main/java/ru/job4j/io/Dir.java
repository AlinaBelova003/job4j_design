package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        File file = new File("c://projects");

        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsolutePath()));
        }

        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }

        System.out.println(String.format("size : %s", file.getTotalSpace()));

        for (File subfile : file.listFiles()) {
            System.out.println(subfile.getAbsoluteFile());
            System.out.println(String.format("name : %s", file.getName()));
            System.out.println(String.format("length : %s", file.length()));
        }
    }
}
