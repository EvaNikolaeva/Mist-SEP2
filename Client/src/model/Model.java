package model;

import Utility.UnnamedPropertyChangeSubject;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  void registerNewUser(String username, String password);
  User login(String username, String password);
  ArrayList<Integer> getAllAvailableGames();
  ArrayList<Integer> getAllPendingGames();

  ArrayList<Integer> getAllUserOwnedGames();
  ArrayList<Integer> getAllUserPendingGames();
  ArrayList<Integer> getAllUserRentedGames();
  ArrayList<Integer> getAllUserIncomingGames();

  void requestGame(int userID, int gameID);
  String getUsername(int userID);
  String getBio(int userID);
  void removeGame(int userID, int gameID);
  void setBio(int userBio, String bio);

  void acceptIncomingGame(int userID, int gameID);
  void declineIncomingGame(int userID, int gameID);

  void addGame(Game game);
  void validateGame(String name, String type, String releaseYear,
      LocalDate rentalFrom, LocalDate rentalTo, String availablePeriod,
      boolean needsDeposit);

  User getOtherUserByID(int userID);
  ArrayList<Integer> getOtherAllUserOwnedGames(int userID);
  ArrayList<Integer> getOtherAllUserPendingGames(int userID);

  void setLocalUserID(int userID);

}
