package data.dao;

import java.util.Collection;
import java.util.List;

public interface UserDao {
    // create
    void add(String userName, String password);

    // read
    List<String> getUserNamesList();

    String getPasswordByUserName(String userName);
}
