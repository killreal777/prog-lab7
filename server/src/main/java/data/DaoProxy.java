package data;

import data.dao.Dao;
import data.sql.DaoSql;
import model.Organization;

import java.util.PriorityQueue;

public class DaoProxy implements Dao {
    private final Dao dao;
    private PriorityQueue<Organization> collection;

    public DaoProxy() {
        this.dao = new DaoSql();
        this.collection = dao.getCollection();
    }

    @Override
    public void add(Organization organization) {
        dao.add(organization);
        collection = dao.getCollection();
    }

    @Override
    public PriorityQueue<Organization> getCollection() {
        return collection;
    }

    @Override
    public void updateById(Integer id, Organization organization) {
        dao.updateById(id, organization);
        collection = dao.getCollection();
    }

    @Override
    public void removeById(Integer id) {
        dao.removeById(id);
        collection = dao.getCollection();
    }

    @Override
    public void removeAllByOwnerLogin(String ownerLogin) {
        dao.removeAllByOwnerLogin(ownerLogin);
        collection = dao.getCollection();
    }

    @Override
    public void add(String userName, String password) {
        dao.add(userName, password);
    }

    @Override
    public String getPasswordByUserName(String userName) {
        return dao.getPasswordByUserName(userName);
    }
}
