package database.sql.utils;

import database.DaoException;
import model.Organization;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementSetter {
    public static void setOrganization(PreparedStatement statement, Organization organization) throws DaoException {
        try {
            int i = 1;
            statement.setInt(i++, organization.getId());
            statement.setString(i++, organization.getName());
            statement.setString(i++, organization.getFullName());
            statement.setInt(i++, organization.getCoordinates().getX());
            statement.setInt(i++, organization.getCoordinates().getY());
            statement.setLong(i++, organization.getAnnualTurnover());
            statement.setInt(i++, organization.getEmployeesCount());
            statement.setInt(i++, organization.getType().ordinal());
            statement.setString(i++, organization.getOfficialAddress().getZipCode());
            statement.setLong(i++, organization.getOfficialAddress().getTown().getX());
            statement.setInt(i++, organization.getOfficialAddress().getTown().getY());
            statement.setFloat(i++, organization.getOfficialAddress().getTown().getZ());
            statement.setString(i, organization.getOfficialAddress().getTown().getName());
        } catch (SQLException e) {
            throw new DaoException();
        }
    }
}
