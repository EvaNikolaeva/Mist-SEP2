package mediator;

import model.Game;
import model.GameList;
import model.Rental;
import model.User;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameListClientClient
{
  void connect() throws InterruptedException;
  void registerNewUser(String username, String password) throws RemoteException;
  User login(String username, String password) throws RemoteException;  //instead of the first 4 from below and last 2 from below
  GameList getGamesFromServer() throws RemoteException;

  void clientRemoveGame(Game game) throws RemoteException;
  void clientSetBio(User user, String bio) throws RemoteException;
  void clientAddGame(User user, Game game) throws RemoteException;
  void clientRequestGame(User requester,Game game) throws RemoteException;
  void clientAcceptIncomingGame(Rental rental) throws RemoteException;
  void clientDeclineIncomingGame(Rental rental) throws RemoteException;

  User getUserFromServer(Game game) throws RemoteException; // instead of the 4 below

  //return type is only for me










//  ArrayList<Integer> getAllAvailableGames() throws RemoteException;
//  ArrayList<Integer> getAllPendingGames() throws RemoteException;

//  ArrayList<Integer> getAllUserOwnedGames(int userID) throws RemoteException;
//  ArrayList<Integer> getAllUserPendingGames(int userID) throws RemoteException;
//  ArrayList<Integer> getAllUserRentedGames(int userID) throws RemoteException;
//  ArrayList<Integer> getAllUserIncomingGames(int userID) throws RemoteException;




//  ArrayList<Integer> getOtherAllUserOwnedGames(int userID)
//      throws RemoteException;
//  ArrayList<Integer> getOtherAllUserPendingGames()
//      throws RemoteException;
//  String getUsername(int gameID) throws RemoteException; //Same
//  String getBio(int gameID) throws RemoteException; //I send the game to retrieve the user
}
