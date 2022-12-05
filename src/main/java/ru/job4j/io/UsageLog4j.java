package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *Логгирование - это процесс записи в файл полезной информации о работе программы.
 * Полученный файл называют лог-файлом. Если приложение работает плохо, то первое что проверяют - это лог файл.
 */
public class UsageLog4j {
    private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
