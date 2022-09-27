package database;

import database.sql.CollectionParser;
import database.sql.DatabaseConnector;
import io.Format;
import io.TextFormatter;
import model.Organization;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class DaoTest extends DatabaseConnector {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/prog-lab7";
        String login = "killreal777";
        String password = "qwerty12345";
        Connection connection = DriverManager.getConnection(url, login, password);
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM organizations;");
        ResultSet resultSet = statement.executeQuery();
        System.out.println(TextFormatter.format("OK OK OK OK OK OK OK", Format.GREEN));
        for (Organization org : CollectionParser.parseOrganizationCollection(resultSet)) {
            org.setCreationDate(LocalDateTime.now());
            System.out.println(org);
        }
    }
}
