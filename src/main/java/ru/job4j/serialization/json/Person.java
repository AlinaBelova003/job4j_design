package ru.job4j.serialization.json;

import java.util.Arrays;

public class Person {
    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] status;

    public Person(boolean sex, int age, Contact contact, String[] status) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", status=" + Arrays.toString(status)
                + '}';
    }
}
