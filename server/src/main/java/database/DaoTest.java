package database;

import database.dao.Dao;
import database.dao.OrganizationDao;
import database.dao.UserDao;
import database.sql.*;
import io.Format;
import io.TextFormatter;
import model.Address;
import model.Coordinates;
import model.Organization;
import model.OrganizationType;

import java.time.LocalDateTime;
import java.util.Collection;

public class DaoTest extends DatabaseConnector {
    public static Dao dao = new DaoSql();

    enum User {
        KIRILL("killreal777", "qwerty12345"),
        MAX("him_maxim", "54321qwerty"),
        MARTIN("seductor_amadeus", "");
        final String userName;
        final String password;
        User(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }
    }

    public static void main(String[] args) {
        showDatabase();
    }

    public static void reset() {
        DaoSql.dropTablesIfExist();
        DaoSql.createTablesIfNotExist();
        createUsers();
        fillDatabase();
    }

    public static void removeAllByOwnerLoginTest() {
        showDatabase();
        System.out.println("\n\n\n\n\n\n");
        dao.removeAllByOwnerLogin("killreal777");
        showDatabase();
    }

    public static void createUsers() {
        for (User user : User.values()) {
            dao.add(user.userName, user.password);
        }
        ok("Users created");
    }

    public static void testPasswords() {
        System.out.println(dao.getPasswordByUserName("killreal777"));
        System.out.println(dao.getPasswordByUserName("seductor_amadeus"));
        System.out.println(dao.getPasswordByUserName("him_maxim"));
    }


    public static void removeByIdTest() {
        showDatabase();
        for (int i = 1; i <= 11; i++) {
            dao.removeById(i);
        }
        ok("11 ORGANIZATIONS REMOVED");
        showDatabase();
    }


    public static void updateTest() {
        Organization organization = org(16);
        organization.setFullName("A.S.PUSHKIN");
        dao.updateById(16, organization);
        ok("UPDATED id16");
        organization = org(13);
        organization.setFullName("MMMAAAAXXX");
        organization.setOwnerLogin("killreal777");
        dao.updateById(13, organization);
        ok("UPDATED id13");
        printCollection(dao.getCollection());
    }

    public static void fillDatabase() {
        for (int i = 1; i < 20; i++) {
            dao.add(org(i));
        }
        ok("DATABASE FILLED");
    }

    public static void showDatabase() {
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
        organization.setOwnerLogin(User.values()[i%3].userName);
        return organization;
    }

    public static void printCollection(Collection<Organization> collection) {
        if (collection.isEmpty())
            System.out.println("Collection is empty");
        for (Organization org : collection) {
            System.out.println(org);
        }
    }
}
