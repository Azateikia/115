package jm.task.core.jdbc.util;

import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionCreator;

import java.sql.*;

public class Util {

    // реализуйте настройку соединения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "SteveO6642SQ";
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Here we go!");
        } catch (SQLException e) {
            System.out.println("Connection just fucked up");
        }
    }
    public static Connection getConnection() {
        return connection;
    }

}
