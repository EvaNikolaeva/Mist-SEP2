package model;

import Utility.UnnamedPropertyChangeSubject;
import mediator.GameListClientClient;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model
    extends UnnamedPropertyChangeSubject, OtherProfileModel, MyProfileModel,
    LoginModel, GameListModel, EditProfileModel, AddGameModel
{
  void setClient(GameListClientClient gameListClientModel);

  void registerNewUser(String username, String password) throws RemoteException;
  User login(String username, String password) throws RemoteException;
  ArrayList<Integer> getAllAvailableGames() throws RemoteException;
  ArrayList<Integer> getAllPendingGames() throws RemoteException;

  ArrayList<Integer> getAllUserOwnedGames() throws RemoteException;
  ArrayList<Integer> getAllUserPendingGames() throws RemoteException;
  ArrayList<Integer> getAllUserRentedGames() throws RemoteException;
  ArrayList<Integer> getAllUserIncomingGames() throws RemoteException;

  void requestGame(int userID, int gameID) throws RemoteException;
  String getUsername(int userID) throws RemoteException;
  String getBio(int userID) throws RemoteException;
  void removeGame(int userID, int gameID) throws RemoteException;
  void setBio(int userID, String bio) throws RemoteException;
  public int getLocalUserId();
  void acceptIncomingGame(int userID, int gameID) throws RemoteException;
  void declineIncomingGame(int userID, int gameID) throws RemoteException;

  void addGame(Game game) throws RemoteException;
  void validateGame(String name, String type, String releaseYear,
      LocalDate rentalFrom, LocalDate rentalTo, String availablePeriod,
      boolean needsDeposit) throws RemoteException;

  User getOtherUserByID(int userID) throws RemoteException;
  ArrayList<Integer> getOtherAllUserOwnedGames(int userID)
      throws RemoteException;
  ArrayList<Integer> getOtherAllUserPendingGames(int userID)
      throws RemoteException;

  void setLocalUserID(int userID);

  Game getGameByID(int userID) throws RemoteException;
}
