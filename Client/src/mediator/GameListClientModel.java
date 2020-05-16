package mediator;

import model.Game;
import model.User;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface GameListClientModel
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

  User getOtherUserByID(int userID);
  ArrayList<Integer> getOtherAllUserOwnedGames(int userID);
  ArrayList<Integer> getOtherAllUserPendingGames(int userID);

  void setLocalUserID(int userID);
}
