package model;

import utility.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;

public interface Model extends UnnamedPropertyChangeSubject
{
  User getUserByID(int id);
  User getUserByCredentials(String username, String password);
  void setBio(int userID, String bio) throws RemoteException;

  void requestGame(int userID, int gameID) throws RemoteException;
  void acceptGame(int userID, int gameID) throws RemoteException;
  void declineGame(int userID, int gameID) throws RemoteException;

  void addGame(int userID, int gameID) throws RemoteException;
  void removeGame(int userID, int gameID) throws RemoteException;
  Game getGameByIndex(int index);
  Game getGameByID(int gameID);
  int getSizeOfGameList();

  void registerUser(String username, String password) throws RemoteException;
}
