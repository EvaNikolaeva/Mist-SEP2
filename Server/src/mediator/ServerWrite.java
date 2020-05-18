package mediator;

import model.Game;

import java.rmi.RemoteException;

public interface ServerWrite extends ServerRead
{
  void setBio(int userID, String bio) throws RemoteException;
  void requestGame(int userID, int gameID) throws RemoteException;
  void acceptGame(int userID, int gameID) throws RemoteException;
  void declineGame(int userID, int gameID) throws RemoteException;
  void addGame(int userID, Game game) throws RemoteException;
  void removeGame(int userID, int gameID) throws RemoteException;
  void registerNewUser(String username, String password) throws RemoteException;
}
