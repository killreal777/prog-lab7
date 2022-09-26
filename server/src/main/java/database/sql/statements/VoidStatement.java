package database.sql.statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Consumer;

public class VoidStatement extends AbstractStatement {
    public VoidStatement(String sql, Consumer<PreparedStatement> setting) {
        super(sql, setting);
    }

    public void execute() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = databaseConnector.getConnection();
            statement = connection.prepareStatement(sql);
            setting.accept(statement);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            databaseConnector.closeConnection(connection);
        }
    }
}
