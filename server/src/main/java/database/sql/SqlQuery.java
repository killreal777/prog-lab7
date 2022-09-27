package database.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public enum SqlQuery {
    ORGANIZATIONS_CREATE_TABLE(readFromFile("organizations_create_table.sql")),

    ORGANIZATIONS_ADD(readFromFile("organizations_add.sql")),

    ORGANIZATIONS_GET_COLLECTION("SELECT * FROM organizations"),

    ORGANIZATIONS_UPDATE_BY_ID(readFromFile("organizations_update_by_id.sql")),

    ORGANIZATIONS_REMOVE_BY_ID("DELETE FROM organizations WHERE organization_id = ?"),

    ORGANIZATIONS_REMOVE_ALL("TRUNCATE organizations");


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
