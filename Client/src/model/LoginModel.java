package model;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface LoginModel
{
  void registerNewUser(String username, String password) throws RemoteException, SQLException;
  User login(String username, String password) throws RemoteException, SQLException;


//  void registerNewUser(String username, String password) throws RemoteException;
//  User login(String username, String password) throws RemoteException;
//  void setLocalUserID(int userID);
}
