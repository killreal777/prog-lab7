package database.sql.statements;

import database.database.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

public abstract class AbstractStatement {
    protected final DatabaseConnector databaseConnector;
    protected final String sql;
    protected final Consumer<PreparedStatement> setting;

    public AbstractStatement(String sql, Consumer<PreparedStatement> setting) {
        this.databaseConnector = new DatabaseConnector();
        this.sql = sql;
        this.setting = setting;
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
