package database.sql;

import database.DaoException;
import database.dao.OrganizationDao;
import model.Organization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.function.Consumer;

public class OrganizationDaoSql implements OrganizationDao {

    public OrganizationDaoSql() {
        String sql = SqlQuery.ORGANIZATIONS_CREATE_TABLE.get();
        try (SqlStatement statement = new SqlStatement(sql)) {
            statement.execute();
        }
    }

    @Override
    public void add(Organization org) throws DaoException {
        String sql = SqlQuery.ORGANIZATIONS_ADD.get();
        Consumer<PreparedStatement> statementSetting = statement ->
                StatementSetter.setOrganization(statement, org, 1);
        try (SqlStatement statement = new SqlStatement(sql, statementSetting)) {
            statement.execute();
        }
    }

    @Override
    public Collection<Organization> getCollection() throws DaoException {
        String sql = SqlQuery.ORGANIZATIONS_GET_COLLECTION.get();
        try (SqlStatement statement = new SqlStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            return CollectionParser.parseOrganizationCollection(resultSet);
        }
    }

    @Override
    public void update_by_id(Integer id, Organization org) throws DaoException {
        String sql = SqlQuery.ORGANIZATIONS_UPDATE_BY_ID.get();
        Consumer<PreparedStatement> statementSetting = statement -> {
            StatementSetter.setOrganization(statement, org, 1);
            StatementSetter.setCheckingId(statement, id, 13);
        };
        try (SqlStatement statement = new SqlStatement(sql, statementSetting)) {
            statement.execute();
        }
    }

    @Override
    public void remove_by_id(Integer id) throws DaoException {
        String sql = SqlQuery.ORGANIZATIONS_REMOVE_BY_ID.get();
        Consumer<PreparedStatement> statementSetting = statement ->
                StatementSetter.setCheckingId(statement, id, 1);
        try (SqlStatement statement = new SqlStatement(sql, statementSetting)) {
            statement.execute();
        }
    }

    @Override
    public void remove_all() throws DaoException {
        String sql = SqlQuery.ORGANIZATIONS_REMOVE_ALL.get();
        try (SqlStatement statement = new SqlStatement(sql)) {
            statement.execute();
        }
    }
}
