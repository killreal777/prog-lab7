package data.dao;

import model.Organization;

import java.util.Collection;

public interface OrganizationDao {
    // create
    void add(Organization organization);

    // read
    Collection<Organization> getCollection();

    // update
    void updateById(Integer id, Organization organization);

    // delete
    void removeById(Integer id);

    void removeAllByOwnerLogin(String ownerLogin);
}
