package model;

import utility.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;

public interface Model extends UnnamedPropertyChangeSubject
{
  User getUserByID(int id);
  User getUserByCredentials(String username, String password);
  void setUserBio(User user, String bio) throws RemoteException;

  void requestGame(User requester, Game game) throws RemoteException;
  void acceptGame(Rental rental) throws RemoteException;
  void declineGame(Rental rental) throws RemoteException;
  GameList getFullListOfGames() throws RemoteException;

  void addGame(Game game) throws RemoteException;
  void removeGame(Game game) throws RemoteException;
  Game getGameByIndex(int index);
  Game getGameByID(int gameID);
  User getUserByGame(Game game);
  int getSizeOfGameList();
RentalList getRentalList();
  void registerUser(String username, String password) throws RemoteException;
}
