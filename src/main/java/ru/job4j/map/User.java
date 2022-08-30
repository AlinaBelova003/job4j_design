package ru.job4j.map;

import java.util.*;

public class User {
   private final String name;
   private final int children;
   private final Calendar birthday;

   public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
   }

   public static void main(String[] args) {
        User person1 = new User("Ivan", 0, new GregorianCalendar(2003, Calendar.APRIL, 10));
        User person2 = new User("Ivan", 0, new GregorianCalendar(2003, Calendar.APRIL, 10));
        System.out.println(person1.equals(person2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

     @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
