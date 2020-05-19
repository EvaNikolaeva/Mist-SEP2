package model;

import utility.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;

public interface Model extends UnnamedPropertyChangeSubject
{
  User getUserByID(int id);
  User getUserByCredentials(String username, String password);
  void setBio(User user, String bio) throws RemoteException;

  void requestGame(User requester, Game game) throws RemoteException;
  void acceptGame(Rental rental) throws RemoteException;
  void declineGame(Rental rental) throws RemoteException;
  GameList getAllGames() throws RemoteException;

  void addGame(User user, Game game) throws RemoteException;
  void removeGame(Game game) throws RemoteException;
  Game getGameByIndex(int index);
  Game getGameByID(int gameID);
  User getUserByGame(Game game);
  int getSizeOfGameList();

  void registerUser(String username, String password) throws RemoteException;
}
