package ru.job4j.generics;

public class Predator extends Animal {
    private int teeth;
    private String wool;


    public Predator(String name, int age, String wool, int teeth) {
        super(name, age);
        this.wool = wool;
        this.teeth = teeth;
    }
}
