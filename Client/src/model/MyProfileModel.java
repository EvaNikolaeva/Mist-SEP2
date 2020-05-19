package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MyProfileModel
{
  User login(String username, String password) throws RemoteException;
  void clientAcceptIncomingGame(Rental rental) throws RemoteException;
  void clientDeclineIncomingGame(Rental rental) throws RemoteException;
  void clientRemoveGame(Game game) throws RemoteException;
  String getUsername();
  String getPassword();
  RentalList getRentalList() throws RemoteException;
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
