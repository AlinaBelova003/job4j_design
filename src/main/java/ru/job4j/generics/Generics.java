package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
    List<Animal> first = new ArrayList<>();
    List<Predator> second = new ArrayList<>();
    List<Tiger> third = new ArrayList<>();
        first.add(new Animal("Dog", 14));
        second.add(new Predator("Leo", 21));
        third.add(new Tiger("Tiger", 7));

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

       /** gen.printBoundedWildCard(first); */
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
       /** gen.printLowerBoundedWildCard(third); */
}
    /**
     * WildCard
     * Специальный символ для обозначения неизвестного типа — «?».
     * Он снимает ограничение; имеет соответствие с любым типом.
     */
    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     *Ограничение сверху позволяет использовать классы, которые наследовались от родительского класса
     */
    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /**
     *Мы можем использовать все классы сверху
     */
    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}
