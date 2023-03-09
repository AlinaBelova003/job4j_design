package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    /**
     * Подключаемся к БД
     */
    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password")
            );
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Читаем свойства в классе Исходников
     */
    private static Properties properties() {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    /**
     * Установите подключение базы данных к серверу PostgreSQL.
     * Создание экземпляра объекта Statement
     * Создание таблицы с колонками
     * executeUpdate: возвращает количество строк, затронутых инструкцией.
     */
    public void createTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s(%s, %s);",
                    tableName,
                    "id serial primary key",
                    "name varchar(255)");
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE %s;",
                    tableName
            );
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s", tableName,
                    "ADD COLUMN %s(%s)", columnName, type
            );
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s", tableName,
                    "DROP COLUMN %s", columnName
            );
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s", tableName,
                    "RENAME COLUMN %s", columnName,
                    "TO %s", newColumnName
            );
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(tableName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor(properties());
        String tableName = "Name";
        tableEditor.createTable(tableName);
    }
}
