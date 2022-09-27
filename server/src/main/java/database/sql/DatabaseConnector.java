package database.sql;

import java.sql.*;

public class DatabaseConnector {
    private final String url;
    private final String login;
    private final String password;

    public DatabaseConnector() {
        this.url = "jdbc:postgresql://localhost:5432/prog-lab7";
        this.login = "killreal777";
        this.password = "qwerty12345";
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
