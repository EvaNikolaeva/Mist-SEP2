package model;

import utility.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface Model
{
  User getUserByID(int id) throws SQLException;
  User getUserByCredentials(String username, String password) throws SQLException;
  void setUserBio(User user, String bio) throws RemoteException, SQLException;

  void requestGame(User requester, Game game) throws RemoteException, SQLException;
  void acceptGame(Rental rental) throws RemoteException, SQLException;
  void declineGame(Rental rental) throws RemoteException, SQLException;
  GameList getFullListOfGames() throws RemoteException, SQLException;
  GameList getRentedGames(User user) throws SQLException;
  void addGame(Game game) throws RemoteException, SQLException;
  void removeGame(Game game) throws RemoteException, SQLException;
  Game getGameByIndex(int index);
  Game getGameByID(int gameID) throws SQLException;
  User getUserByGame(Game game) throws SQLException;
  int getSizeOfGameList() throws SQLException;
RentalList getRentalList() throws SQLException;
  void registerUser(String username, String password) throws SQLException;
  void setGameAvailableTrue(Game game) throws RemoteException, SQLException;
}
