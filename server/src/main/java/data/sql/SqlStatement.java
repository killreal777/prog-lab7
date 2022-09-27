package data.sql;

import java.io.Closeable;
import java.sql.*;
import java.util.function.Consumer;

public class SqlStatement implements Closeable {
    protected final DatabaseConnector databaseConnector;
    protected final String sql;
    protected final Consumer<PreparedStatement> setting;
    private PreparedStatement statement;

    public SqlStatement(String sql) {
        this.databaseConnector = new DatabaseConnector();
        this.sql = sql;
        this.setting = statement -> {}; // no settings
    }

    public SqlStatement(String sql, Consumer<PreparedStatement> setting) {
        this.databaseConnector = new DatabaseConnector();
        this.sql = sql;
        this.setting = setting;
    }

    public boolean execute() {
        try (Connection connection = databaseConnector.getConnection()) {
            statement = connection.prepareStatement(sql);
            setting.accept(statement);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeQuery() {
        try (Connection connection = databaseConnector.getConnection()) {
            statement = connection.prepareStatement(sql);
            setting.accept(statement);
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
