package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class UsageEncoding {
    public String readFiles(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(builder::append);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
    public void writeDataInFile(String path, List<String> data) {
        try (PrintWriter pr = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            data.forEach(pr::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
