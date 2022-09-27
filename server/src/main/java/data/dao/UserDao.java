package data.dao;

public interface UserDao {
    // create
    void add(String userName, String password);

    // read
    String getPasswordByUserName(String userName);
}
