package data;

import data.dao.Dao;
import data.sql.DaoSql;
import model.Organization;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DaoProxy implements Dao {
    private final Dao dao;
    private Collection<Organization> collection;
    private List<String> userNamesList;

    public DaoProxy() {
        this.dao = new DaoSql();
        this.collection = Collections.synchronizedCollection(dao.getCollection());
        this.userNamesList = dao.getUserNamesList();
    }

    @Override
    public void add(Organization organization) {
        dao.add(organization);
        collection = dao.getCollection();
    }

    @Override
    public Collection<Organization> getCollection() {
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
        userNamesList = dao.getUserNamesList();
    }

    @Override
    public List<String> getUserNamesList() {
        return userNamesList;
    }

    @Override
    public String getPasswordByUserName(String userName) {
        return dao.getPasswordByUserName(userName);
    }
}
