package DBSConnection;

import model.User;

public interface UserDAO
{
    User getUserByUserID(int id);
    //User getUser(User user);
    User getUserByCredentials(String username, String password);
    void registerUser(String username, String password);
//    User getUserByIndex(int index);
//    int size();

}
