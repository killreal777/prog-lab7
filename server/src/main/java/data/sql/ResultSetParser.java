package data.sql;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

public class ResultSetParser {

    public static String parsePassword(ResultSet resultSet) {
        try {
            resultSet.next();
            return resultSet.getString("user_password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> parseUserNamesList(ResultSet resultSet) {
        try {
            List<String> userNameList = new ArrayList<>();
            while (resultSet.next())
                userNameList.add(resultSet.getString("user_name"));
            return userNameList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Collection<Organization> parseOrganizationCollection(ResultSet resultSet) {
        try {
            PriorityQueue<Organization> collection = new PriorityQueue<>();
            while (resultSet.next()) {
                collection.add(parseOrganization(resultSet));
            }
            return collection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Organization parseOrganization(ResultSet resultSet) throws SQLException {
        Organization organization = new Organization();
        organization.setId(resultSet.getInt("organization_id"));
        organization.setName(resultSet.getString("organization_name"));
        organization.setFullName(resultSet.getString("organization_full_name"));
        organization.setAnnualTurnover(resultSet.getLong("annual_turnover"));
        organization.setCreationDate(resultSet.getTimestamp("creation_date").toLocalDateTime());
        organization.setType(OrganizationType.getByID(resultSet.getInt("organization_type")));
        organization.setCoordinates(parseCoordinates(resultSet));
        organization.setOfficialAddress(parseAddress(resultSet));
        organization.setOwnerLogin(resultSet.getString("owner_login"));
        return organization;
    }

    private static Coordinates parseCoordinates(ResultSet resultSet) throws SQLException {
        Coordinates coordinates = new Coordinates();
        coordinates.setX(resultSet.getInt("organization_coordinate_x"));
        coordinates.setY(resultSet.getInt("organization_coordinate_y"));
        return coordinates;
    }

    private static Address parseAddress(ResultSet resultSet) throws SQLException {
        Address address = new Address();
        address.setZipCode(resultSet.getString("zip_code"));
        address.setTown(parseLocation(resultSet));
        return address;
    }

    private static Location parseLocation(ResultSet resultSet) throws SQLException {
        Location location = new Location();
        location.setX(resultSet.getLong("location_coordinate_x"));
        location.setY(resultSet.getInt("location_coordinate_y"));
        location.setZ(resultSet.getFloat("location_coordinate_z"));
        location.setName(resultSet.getString("location_name"));
        return location;
    }
}
