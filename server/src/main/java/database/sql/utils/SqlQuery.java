package database.sql.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public enum SqlQuery {
    // init
    CREATE_TABLES("server/src/main/resources/sql/create_tables.sql"),

    // organizations
    ORGANIZATION_ADD("server/src/main/resources/sql/organizations/insert_organization.sql"),

    ORGANIZATION_ADD_IF_MAX(""),

    ORGANIZATION_GET_COLLECTION(""),

    ORGANIZATION_UPDATE_BY_ID(""),

    ORGANIZATION_REMOVE_BY_ID(""),

    ORGANIZATION_REMOVE_BY_ADDRESS(""),

    ORGANIZATION_REMOVE_ALL("");

    // users


    private final String filePath;

    SqlQuery(String filePath) {
        this.filePath = filePath;
    }

    public String get() {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
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
