package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Config config = new Config("./data/app.properties");
        config.load();
        Class.forName(config.value("hibernate.connection.driver_class"));
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String pass = config.value("hibernate.connection.password");
        try (Connection connection = DriverManager.getConnection(url, login, pass)) {
            DatabaseMetaData meta = connection.getMetaData();
            System.out.println(meta.getUserName());
            System.out.println(meta.getURL());
        }

    }


}
