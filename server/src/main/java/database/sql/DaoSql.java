package database.sql;

import database.dao.Dao;
import model.Organization;

import java.util.Collection;

public class DaoSql implements Dao {
    private final UserDaoSql userDaoSql;
    private final OrganizationDaoSql organizationDaoSql;

    public DaoSql() {
        this.userDaoSql = new UserDaoSql();
        this.organizationDaoSql = new OrganizationDaoSql();
        createTablesIfNotExist();
    }

    public static void createTablesIfNotExist() {
        String sql = SqlQuery.CREATE_TABLES.get();
        try (SqlStatement statement = new SqlStatement(sql)) {
            statement.execute();
        }
    }

    public static void dropTablesIfExist() {
        try (SqlStatement statement = new SqlStatement("DROP TABLE IF EXISTS users, organizations")) {
            statement.execute();
        }
    }

    @Override
    public void add(Organization organization) {
        organizationDaoSql.add(organization);
    }

    @Override
    public Collection<Organization> getCollection() {
        return organizationDaoSql.getCollection();
    }

    @Override
    public void updateById(Integer id, Organization organization) {
        organizationDaoSql.updateById(id, organization);
    }

    @Override
    public void removeById(Integer id) {
        organizationDaoSql.removeById(id);
    }

    @Override
    public void removeAllByOwnerLogin(String ownerLogin) {
        organizationDaoSql.removeAllByOwnerLogin(ownerLogin);
    }

    @Override
    public void add(String userName, String password) {
        userDaoSql.add(userName, password);
    }

    @Override
    public String getPasswordByUserName(String userName) {
        return userDaoSql.getPasswordByUserName(userName);
    }
}
