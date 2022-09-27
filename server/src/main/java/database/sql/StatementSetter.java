package database.sql;

import database.DaoException;
import model.Organization;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementSetter {
    static void setOrganization(PreparedStatement statement, Organization org, int parameterStartIndex) {
        try {
            int i = parameterStartIndex;
            statement.setString(i++, org.getName());
            statement.setString(i++, org.getFullName());
            statement.setInt(i++, org.getCoordinates().getX());
            statement.setInt(i++, org.getCoordinates().getY());
            statement.setLong(i++, org.getAnnualTurnover());
            statement.setInt(i++, org.getEmployeesCount());
            statement.setInt(i++, org.getType().ordinal());
            statement.setString(i++, org.getOfficialAddress().getZipCode());
            statement.setLong(i++, org.getOfficialAddress().getTown().getX());
            statement.setInt(i++, org.getOfficialAddress().getTown().getY());
            statement.setFloat(i++, org.getOfficialAddress().getTown().getZ());
            statement.setString(i, org.getOfficialAddress().getTown().getName());
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    static void setCheckingId(PreparedStatement statement, Integer id, int parameterIndex) {
        try {
            statement.setInt(parameterIndex, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void setUsername(PreparedStatement statement, String username, int parameterIndex) {
        try {
            statement.setString(parameterIndex, username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void setUserPassword(PreparedStatement statement, String password, int parameterIndex) {
        try {
            statement.setString(parameterIndex, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
