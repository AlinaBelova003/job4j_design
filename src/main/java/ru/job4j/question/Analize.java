package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    /**
     * Метод считает:
     * сколько добавлено новых пользователей;
     * сколько изменено пользователей;
     * сколько удалено пользователей.
     * Получаем stream в виде коллекции Мар(toMap- возвращяет новую карту из потока) и сразу передаём ключ и значение
     * @param previous Входящая коллекция с исходными данными.
     * @param current  Входящая коллекция с результирующими данными.
     * @return Результат вычисления.

     */
    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> map = previous.stream()
                .collect(Collectors.toMap(User:: getId, User::getName));
        int addCount = 0;
        int changeCount = 0;
        int deleteCount = 0;

        for (User user : current) {
            if (!map.containsKey(user.getId())) {
                addCount++;
            } else if (!map.equals(user.getName()) && map.equals(user.getId())) {
                changeCount++;
            }

        }
        return diff(addCount, changeCount);
    }
}
