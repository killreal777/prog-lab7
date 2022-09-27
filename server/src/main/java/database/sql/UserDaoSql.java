package database.sql;

import database.dao.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Consumer;

public class UserDaoSql implements UserDao {

    @Override
    public void add(String userName, String password) {
        String sql = SqlQuery.USERS_ADD.get();
        Consumer<PreparedStatement> statementSetting = statement -> {
            StatementParametersSetter.setUserName(statement, userName, 1);
            StatementParametersSetter.setPassword(statement, password, 2);
        };
        try (SqlStatement statement = new SqlStatement(sql, statementSetting)) {
            statement.execute();
        }
    }

    @Override
    public String getPasswordByUserName(String userName) {
        String sql = SqlQuery.USERS_GET_PASSWORD_BY_LOGIN.get();
        Consumer<PreparedStatement> statementSetting = statement ->
                StatementParametersSetter.setUserName(statement, userName, 1);
        try (SqlStatement statement = new SqlStatement(sql, statementSetting)) {
            ResultSet resultSet = statement.executeQuery();
            return ResultSetParser.parsePassword(resultSet);
        }
    }
}
