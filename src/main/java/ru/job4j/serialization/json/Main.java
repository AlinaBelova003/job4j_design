package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(false, 30, new Contact("11- 111"), new String[] {"Student,Married"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Модифицируем json-строку */
        final String personJson =
                "{"
                + "\"sex\":false,"
                + "\"age\":30,"
                + "\"contact\": "
                + "{"
                + "\"phone\":\"7(924)111-111-11-11\""
                + "},"
                + "\"status\":"
                + "[\"Student\", \"Free\"]"
                + "}";

        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
        /**
         * Пример для демонстрации записи в XML формате.
         * XML - инструмет для хранения и передачи данных, обернутый в теги
         */
        Cat cat = new Cat("Barsik", 5, false, new Contact("7(924) - 866-66-66"), new
                String[] {"Smart, Lazy"});
        final Gson gson1 = new GsonBuilder().create();
        System.out.println(gson1.toJson(cat));
    }
}
