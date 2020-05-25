package DBSConnection;

import model.User;

import java.sql.SQLException;

public interface UserDAO
{
    User getUserByUserID(int id) throws SQLException;
    User getUser(User user) throws SQLException;
    User getUserByCredentials(String username, String password) throws SQLException;
    void registerUser(String username, String password) throws SQLException;
   // User getUserByIndex(int index) throws SQLException;
    int size() throws SQLException;
    void setBio(User user, String bio) throws SQLException;
//    String getBio(User user) throws SQLException;

}
