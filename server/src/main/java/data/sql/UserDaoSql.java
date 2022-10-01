package data.sql;

import data.dao.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.function.Consumer;

public class UserDaoSql implements UserDao {

    @Override
    public synchronized void add(String userName, String password) {
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
    public synchronized List<String> getUserNamesList() {
        String sql = SqlQuery.USERS_GET_USER_NAMES_LIST.get();
        try (SqlStatement statement = new SqlStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            return ResultSetParser.parseUserNamesList(resultSet);
        }
    }

    @Override
    public synchronized String getPasswordByUserName(String userName) {
        String sql = SqlQuery.USERS_GET_PASSWORD_BY_LOGIN.get();
        Consumer<PreparedStatement> statementSetting = statement ->
                StatementParametersSetter.setUserName(statement, userName, 1);
        try (SqlStatement statement = new SqlStatement(sql, statementSetting)) {
            ResultSet resultSet = statement.executeQuery();
            return ResultSetParser.parsePassword(resultSet);
        }
    }
}
