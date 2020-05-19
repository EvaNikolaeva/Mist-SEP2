package model;

import java.rmi.RemoteException;

public interface EditProfileModel {
    User login(String username, String password) throws RemoteException;

    String getUsername();

    String getPassword();

    void setBio(User user, String bioText) throws RemoteException;
//  void setBio(int userID, String bio) throws RemoteException;
//  String getBio(int userID) throws RemoteException;
//  int getLocalUserId();
}
