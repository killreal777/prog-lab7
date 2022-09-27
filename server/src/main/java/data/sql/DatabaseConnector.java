package data.sql;

import java.sql.*;

public class DatabaseConnector {
    private final String url;
    private final String login;
    private final String password;

    public DatabaseConnector() {
        this.url = "jdbc:postgresql://localhost:5432/prog-lab7";
        this.login = "lab7";
        this.password = "qwerty12345";
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
