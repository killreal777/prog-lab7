package database.sql;

import database.dao.UserDao;

import java.sql.ResultSet;

public class UserDaoSql implements UserDao {

    public UserDaoSql() {
        String sql = SqlQuery.USERS_CREATE_TABLE.get();
        try (SqlStatement statement = new SqlStatement(sql)) {
            statement.execute();
        }
    }

    public static void truncateTable() {
        try (SqlStatement statement = new SqlStatement("TRUNCATE users")) {
            statement.execute();
        }
    }

    @Override
    public void add(String userName, String password) {
        String sql = SqlQuery.USERS_ADD.get();
        try (SqlStatement statement = new SqlStatement(sql)) {
            statement.execute();
        }
    }

    @Override
    public String getPasswordByUsername(String userName) {
        String sql = SqlQuery.USERS_GET_PASSWORD_BY_USERNAME.get();
        try (SqlStatement statement = new SqlStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            return ResultSetParser.parsePassword(resultSet);
        }
    }
}
