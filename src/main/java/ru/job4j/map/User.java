package ru.job4j.map;

import java.util.*;

public class User {
   private String name;
   private int children;
   private Calendar birthday;

   public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
   }

   public static void main(String[] args) {
        User person1 = new User("Ivan", 0, new GregorianCalendar(2003, Calendar.APRIL, 10));
        System.out.println(person1.hashCode());
        User person2 = new User("Ivan", 0, new GregorianCalendar(2003, Calendar.APRIL, 10));
        System.out.println(person2.hashCode());
        Map<User, Object> map = new HashMap<>(16);
        map.put(person1, new Object());
        map.put(person2, new Object());

        System.out.println(person1.equals(person2));
    }

}
