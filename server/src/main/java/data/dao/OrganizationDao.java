package data.dao;

import model.Organization;

import java.util.PriorityQueue;

public interface OrganizationDao {
    // create
    void add(Organization organization);

    // read
    PriorityQueue<Organization> getCollection();

    // update
    void updateById(Integer id, Organization organization);

    // delete
    void removeById(Integer id);

    void removeAllByOwnerLogin(String ownerLogin);
}
