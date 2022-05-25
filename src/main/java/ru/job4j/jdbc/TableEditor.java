package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;
    private Statement statement;

    public TableEditor(Properties properties) throws SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException {
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String pass = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, pass);
        this.statement = connection.createStatement();
    }

    public void createTable(String tableName) throws SQLException {
        String sql = "create table if not exists " + tableName + "();";
        statement.execute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = "drop table " + tableName + ";";
        statement.execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = "alter table " + tableName + " add column " + columnName + " " + type + ";";
        statement.execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = "alter table " + tableName + " drop column " + columnName + ";";
        statement.execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = "alter table " + tableName + " rename column " + columnName + " to " + newColumnName + ";";
        statement.execute(sql);
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
        if (statement != null) {
            statement.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("temp_demo");
            tableEditor.addColumn("temp_demo", "id", "serial primary key");
            tableEditor.addColumn("temp_demo", "name", "text");
            System.out.println(tableEditor.getTableScheme("temp_demo"));
            tableEditor.renameColumn("temp_demo", "name", "newName");
            System.out.println(tableEditor.getTableScheme("temp_demo"));
            tableEditor.dropColumn("temp_demo", "newName");
            System.out.println(tableEditor.getTableScheme("temp_demo"));
            tableEditor.dropTable("temp_demo");
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}
