package database.database;

import database.sql.utils.SqlQuery;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnectorTest extends DatabaseConnector {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        String sql = SqlQuery.CREATE_TABLES.get();
        PreparedStatement statement = new DatabaseConnector().getConnection().prepareStatement(sql);
        statement.execute();
    }
}
