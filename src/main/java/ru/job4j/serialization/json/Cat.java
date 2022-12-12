package ru.job4j.serialization.json;

import java.util.Arrays;

public class Cat {
    private final String name;
    private final int weight;
    private final boolean castrated;
    private final Contact contact;
    private final String[] status;

   public Cat(String name, int weight, boolean castrated, Contact contact, String[] status) {
        this.name = name;
        this.weight = weight;
        this.castrated = castrated;
        this.contact = contact;
        this.status = status;
    }


    @Override
    public String toString() {
        return "Cat{"
                + "name='" + name + '\''
                + ", weight=" + weight
                + ", castrated=" + castrated
                + ", contact=" + contact
                + ", status=" + Arrays.toString(status)
                + '}';
    }
}
