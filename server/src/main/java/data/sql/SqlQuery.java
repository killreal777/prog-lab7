package data.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public enum SqlQuery {

    CREATE_TABLES("CREATE TABLE IF NOT EXISTS users (\n" +
            "     user_name                    VARCHAR(32)    UNIQUE PRIMARY KEY,\n" +
            "     user_password                VARCHAR(128)\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS organizations (\n" +
            "     organization_id              INT            NOT NULL    UNIQUE PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n" +
            "     organization_name            TEXT           NOT NULL,\n" +
            "     organization_full_name       TEXT           NOT NULL    UNIQUE,\n" +
            "     organization_coordinate_x    INT            NOT NULL    CHECK (organization_coordinate_x > -535),\n" +
            "     organization_coordinate_y    INT            NOT NULL    CHECK (organization_coordinate_y <= 630),\n" +
            "     annual_turnover              BIGINT         NOT NULL    CHECK (annual_turnover > 0),\n" +
            "     employees_count              INT            NOT NULL    CHECK (employees_count > 0),\n" +
            "     organization_type            INT            NOT NULL    CHECK (organization_type IN (0, 1, 2, 3)),\n" +
            "     zip_code                     VARCHAR(16)    NOT NULL,\n" +
            "     location_coordinate_x        BIGINT         NOT NULL,\n" +
            "     location_coordinate_y        INT            NOT NULL,\n" +
            "     location_coordinate_z        FLOAT          NOT NULL,\n" +
            "     location_name                TEXT           NOT NULL,\n" +
            "     creation_date                TIMESTAMP      NOT NULL    DEFAULT CURRENT_TIMESTAMP,\n" +
            "     owner_login                  VARCHAR(32)    NOT NULL,   FOREIGN KEY (owner_login) REFERENCES users(user_name)\n" +
            ");"),

    ORGANIZATIONS_ADD("INSERT INTO organizations (organization_name, organization_full_name,\n" +
            "                           organization_coordinate_x, organization_coordinate_y,\n" +
            "                           annual_turnover, employees_count, organization_type,\n" +
            "                           zip_code,\n" +
            "                           location_coordinate_x, location_coordinate_y, location_coordinate_z,\n" +
            "                           location_name,\n" +
            "                           owner_login)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"),

    ORGANIZATIONS_GET_COLLECTION("SELECT * FROM organizations"),

    ORGANIZATIONS_UPDATE_BY_ID("UPDATE organizations SET  organization_name = ?, organization_full_name = ?,\n" +
            "                          organization_coordinate_x = ?, organization_coordinate_y = ?,\n" +
            "                          annual_turnover = ?, employees_count = ?,\n" +
            "                          organization_type = ?,\n" +
            "                          zip_code = ?,\n" +
            "                          location_coordinate_x = ?, location_coordinate_y = ?, location_coordinate_z = ?,\n" +
            "                          location_name = ?,\n" +
            "                          owner_login = ?\n" +
            "WHERE organization_id = ?;"),

    ORGANIZATIONS_REMOVE_BY_ID("DELETE FROM organizations WHERE organization_id = ?"),

    ORGANIZATIONS_REMOVE_ALL_BY_OWNER_LOGIN("DELETE FROM organizations WHERE owner_login = ?"),

    USERS_ADD("INSERT INTO users (user_name, user_password) VALUES (?, ?)"),

    USERS_GET_USER_NAMES_LIST("SELECT user_name FROM users"),

    USERS_GET_PASSWORD_BY_LOGIN("SELECT user_password FROM users WHERE user_name = ?");


    private final String query;

    SqlQuery(String query) {
        this.query = query;
    }

    public String get() {
        return query;
    }

    private static String readFromFile(String fileName) {
        String directory = "server/src/main/resources/sql/";
        try (FileInputStream fileInputStream = new FileInputStream(directory + fileName)) {
            Scanner scanner = new Scanner(fileInputStream);
            scanner.useDelimiter("\\Z");
            String query = scanner.next();
            scanner.close();
            return query;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
