package database.sql;

import java.sql.*;
import java.util.function.Consumer;

public class SqlStatement {
    protected final DatabaseConnector databaseConnector;
    protected final String sql;
    protected final Consumer<PreparedStatement> setting;
    private ResultSet resultSet;
    private boolean booleanResult;

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

    public boolean executeVoid() {
        execute(this::executeWithBooleanResult);
        return booleanResult;
    }

    public ResultSet executeResulted() {
        execute(this::executeWithResultSet);
        return resultSet;
    }

    private void execute(Consumer<PreparedStatement> executionMethod) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = databaseConnector.getConnection();
            statement = connection.prepareStatement(sql);
            setting.accept(statement);
            executionMethod.accept(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null)
                closeStatement(statement);
            if (connection != null)
                databaseConnector.closeConnection(connection);
        }
    }

    private void executeWithBooleanResult(PreparedStatement statement) {
        try {
            booleanResult = statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeWithResultSet(PreparedStatement statement) {
        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void closeStatement(Statement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
