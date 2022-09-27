package database.sql;

import database.DaoException;
import database.dao.OrganizationDao;
import model.Organization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.function.Consumer;

public class OrganizationDaoSql implements OrganizationDao {

    @Override
    public void add(Organization organization) throws DaoException {
        String sql = SqlQuery.ORGANIZATION_ADD.get();
        Consumer<PreparedStatement> setting = statement -> StatementSetter.setOrganization(statement, organization);
        new SqlStatement(sql, setting).executeVoid();
    }

    @Override
    public Collection<Organization> getCollection() throws DaoException {
        String sql = SqlQuery.ORGANIZATION_GET_COLLECTION.get();
        ResultSet resultSet = new SqlStatement(sql).executeResulted();
        return CollectionParser.parseOrganizationCollection(resultSet);
    }

    @Override
    public void update_by_id(Integer id, Organization organization) throws DaoException {
        String sql = SqlQuery.ORGANIZATION_UPDATE_BY_ID.get();
        Consumer<PreparedStatement> setting = statement -> StatementSetter.setOrganization(statement, organization);
        new SqlStatement(sql, setting).executeVoid();
    }

    @Override
    public void remove_by_id(Integer id) throws DaoException {
        String sql = SqlQuery.ORGANIZATION_REMOVE_BY_ID.get();
        Consumer<PreparedStatement> setting = statement -> {};
        new SqlStatement(sql, setting).executeVoid();
    }

    @Override
    public void remove_all() throws DaoException {
        String sql = SqlQuery.ORGANIZATION_REMOVE_ALL.get();
        new SqlStatement(sql).executeVoid();
    }
}
