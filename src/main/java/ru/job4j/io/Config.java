package ru.job4j.io;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values =  new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Должен считать все ключи в карту values.
     * Метод load должен загружать пару ключ-значение в Map values.
     *
     */
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            for (String line : reader.lines().toList()) {
                String[] array = line.split("=");
                String kay = array[0];
                String value = array[1];
                values.put(kay, value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
       return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/app.properties.txt"));


    }

}
