package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Читаем файл properties
 */
public class ConnectionDemo {
    /**
     *JDBC – это API, т.е. набор вспомогательных классов, которое позволяет работать с базами данных.
     * Class.forName("org.postgresql.Driver"); - подключение к базе
     * Чтобы получить подключение нужно воспользоваться классом DriverManager,
     * Получили объект типа Connection. Если он не равен null, то установлено подключение и мы можем выполнять запросы к Бд
     * Для получения информации о БД и ее внутренней структуре существует класс DatabaseMetaData.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("org.postgresql.Driver");
        Config config = new Config("./../app.properties");
        config.load();
        try (Connection connection = DriverManager.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }

    }
}
