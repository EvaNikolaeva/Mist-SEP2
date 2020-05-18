package mediator;

import model.Game;
import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRead extends Remote
{
  User getUserByID(int id) throws RemoteException;
  User getUserByCredentials(String username, String password) throws RemoteException;
  Game getGameByIndex(int index) throws RemoteException;
  Game getGameByID(int gameID) throws RemoteException;
  int getSizeOfGameList() throws RemoteException;
}
