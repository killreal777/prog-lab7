package database.dao;

import database.DaoException;
import model.Address;
import model.Organization;

import java.util.Collection;

public interface OrganizationDao {
    // create
    void add(Organization organization) throws DaoException;

    // read
    Collection<Organization> getCollection() throws DaoException;

    // update
    void update_by_id(Integer id, Organization organization) throws DaoException;

    // delete
    void remove_by_id(Integer id) throws DaoException;

    void remove_all() throws DaoException;
}
