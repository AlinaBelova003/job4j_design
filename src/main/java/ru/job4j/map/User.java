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
        User person2 = new User("Ivan", 0, new GregorianCalendar(2003, Calendar.APRIL, 10));
        Map<User, Object> map = new HashMap<>(16);
        map.put(person1, new Object());
        map.put(person2, new Object());
        int hashCode1 = person1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        int hashCode2 = person2.hashCode();
       int hash2 = hashCode2 ^ (hashCode2 >>> 16);
       int bucket2 = hash2 & 15;
        System.out.println(person1.equals(person2));
       System.out.println(map);
    }

}
