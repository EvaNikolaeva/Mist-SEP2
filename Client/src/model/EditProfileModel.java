package model;

import java.rmi.RemoteException;

public interface EditProfileModel
{
  User login(String username, String password) throws RemoteException;

//  void setBio(int userID, String bio) throws RemoteException;
//  String getBio(int userID) throws RemoteException;
//  int getLocalUserId();
}
