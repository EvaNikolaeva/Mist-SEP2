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
  void connect() throws InterruptedException;
  void registerNewUser(String username, String password) throws RemoteException;
  User login(String username, String password) throws RemoteException;
  ArrayList<Integer> getAllAvailableGames() throws RemoteException;
  ArrayList<Integer> getAllPendingGames() throws RemoteException;

  ArrayList<Integer> getAllUserOwnedGames(int userID) throws RemoteException;
  ArrayList<Integer> getAllUserPendingGames(int userID) throws RemoteException;
  ArrayList<Integer> getAllUserRentedGames(int userID) throws RemoteException;
  ArrayList<Integer> getAllUserIncomingGames(int userID) throws RemoteException;

  void requestGame(int userID, int gameID) throws RemoteException;
  String getUsername(int userID) throws RemoteException;
  String getBio(int userID) throws RemoteException;
  void removeGame(int userID, int gameID) throws RemoteException;
  void setBio(int userBio, String bio) throws RemoteException;

  void acceptIncomingGame(int userID, int gameID) throws RemoteException;
  void declineIncomingGame(int userID, int gameID) throws RemoteException;
  Game getGameById(int gameID) throws RemoteException;
  void addGame(Game game) throws RemoteException;

  User getOtherUserByID(int userID) throws RemoteException;
  ArrayList<Integer> getOtherAllUserOwnedGames()
      throws RemoteException;
  ArrayList<Integer> getOtherAllUserPendingGames()
      throws RemoteException;
}
