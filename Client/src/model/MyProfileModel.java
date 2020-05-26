package model;

import Utility.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MyProfileModel extends UnnamedPropertyChangeSubject
{
  User login(String username, String password) throws RemoteException, SQLException;

  void clientAcceptIncomingGame(Rental rental) throws RemoteException, SQLException;

  void clientDeclineIncomingGame(Rental rental) throws RemoteException, SQLException;

  void clientRemoveGame(Game game) throws RemoteException, SQLException;

  String getUsername();

  String getPassword();

  RentalList getRentalList() throws RemoteException, SQLException;

  void setGameAvailabilityTrue(Game game) throws RemoteException, SQLException;

  GameList getAllGamesFromServer() throws RemoteException, SQLException;

  GameList getAllRentedGames(User user) throws RemoteException, SQLException;
//  ArrayList<Integer> getAllUserOwnedGames() throws RemoteException;
//  ArrayList<Integer> getAllUserPendingGames() throws RemoteException;
//  ArrayList<Integer> getAllUserRentedGames() throws RemoteException;
//  ArrayList<Integer> getAllUserIncomingGames() throws RemoteException;
//
//  String getUsername(int userID) throws RemoteException;
//  String getBio(int userID) throws RemoteException;
//  void removeGame(int userID, int gameID) throws RemoteException;
//  void acceptIncomingGame(int userID, int gameID) throws RemoteException;
//  void declineIncomingGame(int userID, int gameID) throws RemoteException;
//  User getOtherUserByID(int userID) throws RemoteException;
//  public int getLocalUserId();
}
