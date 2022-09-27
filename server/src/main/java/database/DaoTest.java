package database;

import creators.OrganizationCreator;
import database.dao.OrganizationDao;
import database.sql.*;
import io.Format;
import io.Terminal;
import io.TextFormatter;
import model.Address;
import model.Coordinates;
import model.Organization;
import model.OrganizationType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;

public class DaoTest extends DatabaseConnector {
    public static void main(String[] args) {
        updateTest();
    }


    public static void removeByIdTest() {
        OrganizationDao dao = new OrganizationDaoSql();
        showDatabase();
        for (int i = 42; i <= 52; i++) {
            dao.remove_by_id(i);
        }
        ok("11 ORGANIZATIONS REMOVED");
        showDatabase();
    }

    public static void removeAllTest() {
        OrganizationDao dao = new OrganizationDaoSql();
        printCollection(dao.getCollection());
        dao.remove_all();
        ok("ALL ORGANIZATIONS REMOVED");
        printCollection(dao.getCollection());
    }

    public static void test2() {
        OrganizationDao dao = new OrganizationDaoSql();
        dao.remove_all();
    }

    public static void updateTest() {
        OrganizationDao dao = new OrganizationDaoSql();
        Organization organization = org(21);
        organization.setFullName("A.S.PUSHKIN");
        dao.update_by_id(55, organization);
        ok("UPDATED id55");
        organization = org(22);
        organization.setFullName("MMMAAAAXXX");
        dao.update_by_id(41, organization);
        ok("UPDATED id41");
        printCollection(dao.getCollection());
    }

    public static void fillDatabase() {
        OrganizationDao dao = new OrganizationDaoSql();
        for (int i = 1; i < 20; i++) {
            dao.add(org(i));
        }
        ok("DATABASE FILLED");
    }

    public static void showDatabase() {
        OrganizationDao dao = new OrganizationDaoSql();
        printCollection(dao.getCollection());
    }


    public static void ok(String message) {
        System.out.println(TextFormatter.format(message, Format.GREEN));
    }

    public static Organization org(Integer i) {
        Organization organization = new Organization();
        organization.setName(i.toString());
        organization.setFullName(i.toString());
        organization.setType(OrganizationType.getByID(i % 3));
        organization.setAnnualTurnover(i.longValue());
        organization.setEmployeesCount(i);
        organization.setCoordinates(new Coordinates(i, i));
        organization.setOfficialAddress(new Address(i.toString(), i.longValue(), i, i.floatValue(), i.toString()));
        return organization;
    }

    public static void printCollection(Collection<Organization> collection) {
        if (collection.isEmpty())
            System.out.println("Collection is empty");
        for (Organization org : collection) {
            org.setCreationDate(LocalDateTime.now());
            System.out.println(org);
        }
    }
}
