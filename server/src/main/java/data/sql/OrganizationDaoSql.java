package data.sql;

import data.dao.OrganizationDao;
import model.Organization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.function.Consumer;

public class OrganizationDaoSql implements OrganizationDao {

    @Override
    public void add(Organization org) {
        String sql = SqlQuery.ORGANIZATIONS_ADD.get();
        Consumer<PreparedStatement> statementSetting = statement ->
                StatementParametersSetter.setOrganization(statement, org, 1);
        try (SqlStatement statement = new SqlStatement(sql, statementSetting)) {
            statement.execute();
        }
    }

    @Override
    public Collection<Organization> getCollection() {
        String sql = SqlQuery.ORGANIZATIONS_GET_COLLECTION.get();
        try (SqlStatement statement = new SqlStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            return ResultSetParser.parseOrganizationCollection(resultSet);
        }
    }

    @Override
    public void updateById(Integer id, Organization org) {
        String sql = SqlQuery.ORGANIZATIONS_UPDATE_BY_ID.get();
        Consumer<PreparedStatement> statementSetting = statement -> {
            StatementParametersSetter.setOrganization(statement, org, 1);
            StatementParametersSetter.setId(statement, id, 14);
        };
        try (SqlStatement statement = new SqlStatement(sql, statementSetting)) {
            statement.execute();
        }
    }

    @Override
    public void removeById(Integer id) {
        String sql = SqlQuery.ORGANIZATIONS_REMOVE_BY_ID.get();
        Consumer<PreparedStatement> statementSetting = statement ->
                StatementParametersSetter.setId(statement, id, 1);
        try (SqlStatement statement = new SqlStatement(sql, statementSetting)) {
            statement.execute();
        }
    }

    @Override
    public void removeAllByOwnerLogin(String ownerLogin) {
        String sql = SqlQuery.ORGANIZATIONS_REMOVE_ALL_BY_OWNER_LOGIN.get();
        Consumer<PreparedStatement> statementSetting = statement ->
                StatementParametersSetter.setUserName(statement, ownerLogin, 1);
        try (SqlStatement statement = new SqlStatement(sql, statementSetting)) {
            statement.execute();
        }
    }
}
