package mediator;

import model.Game;
import model.GameList;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteGameListModel extends Remote
{
  GameList getGameList() throws RemoteException;
  void addGame(Game game) throws RemoteException;
  void removeGame(int id) throws RemoteException;
}
