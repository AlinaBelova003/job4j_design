package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *Логгирование - это процесс записи в файл полезной информации о работе программы.
 * Полученный файл называют лог-файлом. Если приложение работает плохо, то первое что проверяют - это лог файл.
 */
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        String name = "Belova Alina";
        int age = 19;
        byte monthCourse = 9;
        short monthEverything = 14;
        long hours = 324000000;
        float motivation = 23.56F;
        double finish = 1.04;
        boolean beDeveloper = true;
        char xs = 'a';
        LOG.debug("User info name: {}, age: {}, months spent on the course job4j: {}, "
                + " months spent on the everything programming: {}, hours: {} , level motivation: {},"
                + "i will finish in {}, i want to be a developer: {}, хз: {} ", name, age, monthCourse, monthEverything, hours, motivation, finish, beDeveloper, xs);

    }
}
