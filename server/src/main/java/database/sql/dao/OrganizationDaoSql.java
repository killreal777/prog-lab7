package database.sql.dao;

import database.DaoException;
import database.dao.OrganizationDao;
import database.sql.utils.ResultParser;
import database.sql.utils.SqlQuery;
import database.sql.utils.StatementSetter;
import database.sql.statements.ResultedStatement;
import database.sql.statements.VoidStatement;
import model.Address;
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
        new VoidStatement(sql, setting).execute();
    }

    @Override
    public void addIfMax(Organization organization) throws DaoException {
        String sql = SqlQuery.ORGANIZATION_ADD_IF_MAX.get();
        Consumer<PreparedStatement> setting = statement -> StatementSetter.setOrganization(statement, organization);
        new VoidStatement(sql, setting).execute();
    }

    @Override
    public Collection<Organization> getCollection() throws DaoException {
        String sql = SqlQuery.ORGANIZATION_GET_COLLECTION.get();
        Consumer<PreparedStatement> noSettings = statement -> {};
        ResultSet resultSet = new ResultedStatement(sql, noSettings).execute();
        return ResultParser.parseOrganizationCollection(resultSet);
    }

    @Override
    public void update_by_id(Integer id, Organization organization) throws DaoException {
        String sql = SqlQuery.ORGANIZATION_UPDATE_BY_ID.get();
        Consumer<PreparedStatement> setting = statement -> StatementSetter.setOrganization(statement, organization);
        new VoidStatement(sql, setting).execute();
    }

    @Override
    public void remove_by_id(Integer id) throws DaoException {
        String sql = SqlQuery.ORGANIZATION_REMOVE_BY_ID.get();
        Consumer<PreparedStatement> setting = statement -> {};
        new VoidStatement(sql, setting).execute();
    }

    @Override
    public void remove_by_address(Address address) throws DaoException {
        String sql = SqlQuery.ORGANIZATION_REMOVE_BY_ADDRESS.get();
        Consumer<PreparedStatement> setting = statement -> {};
        new VoidStatement(sql, setting).execute();
    }

    @Override
    public void remove_all() throws DaoException {
        String sql = SqlQuery.ORGANIZATION_REMOVE_ALL.get();
        Consumer<PreparedStatement> setting = statement -> {};
        new VoidStatement(sql, setting).execute();
    }


}
