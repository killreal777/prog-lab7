package database.sql.statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class ResultedStatement extends AbstractStatement {

    public ResultedStatement(String sql, Consumer<PreparedStatement> setting) {
        super(sql, setting);
    }

    public ResultSet execute() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = databaseConnector.getConnection();
            statement = connection.prepareStatement(sql);
            setting.accept(statement);
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            databaseConnector.closeConnection(connection);
        }
    }
}
