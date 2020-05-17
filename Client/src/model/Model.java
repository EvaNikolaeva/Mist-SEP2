package model;

import Utility.UnnamedPropertyChangeSubject;
import mediator.GameListClientModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  void setClient(GameListClientModel gameListClientModel);

  void registerNewUser(String username, String password);
  User login(String username, String password);
  ArrayList<Integer> getAllAvailableGames();
  ArrayList<Integer> getAllPendingGames();

  ArrayList<Integer> getAllUserOwnedGames();
  ArrayList<Integer> getAllUserPendingGames();
  ArrayList<Integer> getAllUserRentedGames();
  ArrayList<Integer> getAllUserIncomingGames();

  void requestGame(int userID, int gameID) throws RemoteException;
  String getUsername(int userID) throws RemoteException;
  String getBio(int userID) throws RemoteException;
  void removeGame(int userID, int gameID) throws RemoteException;
  void setBio(int userBio, String bio) throws RemoteException;

  void acceptIncomingGame(int userID, int gameID) throws RemoteException;
  void declineIncomingGame(int userID, int gameID) throws RemoteException;

  void addGame(Game game) throws RemoteException;
  void validateGame(String name, String type, String releaseYear,
      LocalDate rentalFrom, LocalDate rentalTo, String availablePeriod,
      boolean needsDeposit) throws RemoteException;

  User getOtherUserByID(int userID);
  ArrayList<Integer> getOtherAllUserOwnedGames(int userID);
  ArrayList<Integer> getOtherAllUserPendingGames(int userID);

  void setLocalUserID(int userID);

}
