package model;

import java.rmi.RemoteException;

public interface EditProfileModel
{
  void setBio(int userID, String bio) throws RemoteException;
  String getBio(int userID) throws RemoteException;
  int getLocalUserId();
}
