package model;

import java.rmi.RemoteException;

public interface LoginModel
{
  void registerNewUser(String username, String password) throws RemoteException;
  User login(String username, String password) throws RemoteException;


//  void registerNewUser(String username, String password) throws RemoteException;
//  User login(String username, String password) throws RemoteException;
//  void setLocalUserID(int userID);
}
