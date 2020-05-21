package DBSConnection;

import model.User;

import java.sql.SQLException;

public interface UserDAO
{
    User getUserByUserID(int id) throws SQLException;
    //User getUser(User user);
    User getUserByCredentials(String username, String password) throws SQLException;
    void registerUser(String username, String password) throws SQLException;
//    User getUserByIndex(int index);
//    int size();

}
