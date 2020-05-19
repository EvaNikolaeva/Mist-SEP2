package mediator;

import model.Game;
import model.Rental;
import model.User;

import java.rmi.RemoteException;

public interface ServerWrite extends ServerRead
{
  void addClient(RemoteGameListClient client) throws RemoteException;
  void registerClient(String username, String password) throws RemoteException;
  User loginClient(String username, String password) throws RemoteException;

  void removeGame(Game game) throws RemoteException;
  void setBio(User user, String bio) throws RemoteException;
  void addGame(User user, Game game) throws RemoteException;

  void requestGame(User requester,Game game) throws RemoteException;
  void acceptIncomingGame(Rental rental) throws RemoteException;
  void declineIncomingGame(Rental rental) throws RemoteException;

  //READ AND WRITE INTERFACE REPLACE REMOTE INTERFACE



//  void setBio(int userID, String bio) throws RemoteException;
//  void requestGame(int userID, int gameID) throws RemoteException;
//  void acceptGame(int userID, int gameID) throws RemoteException;
//  void declineGame(int userID, int gameID) throws RemoteException;
//  void addGame(int userID, Game game) throws RemoteException;
//  void removeGame(int userID, int gameID) throws RemoteException;
//  void registerNewUser(String username, String password) throws RemoteException;
}
