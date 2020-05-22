package model;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface EditProfileModel {
    User login(String username, String password) throws RemoteException, SQLException;

    String getUsername();

    String getPassword();

    void setBio(User user, String bioText) throws RemoteException, SQLException;
//  void setBio(int userID, String bio) throws RemoteException;
//  String getBio(int userID) throws RemoteException;
//  int getLocalUserId();
}
