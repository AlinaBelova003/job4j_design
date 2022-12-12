package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    }
}
