package mediator;

import model.Game;
import model.GameList;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameListClientModel
{
  void connect()
      throws RemoteException, NotBoundException, MalformedURLException,
      InterruptedException;
  GameList getGameList() throws RemoteException;
  void addGame(Game game) throws RemoteException;
  void removeGame(int id) throws RemoteException;
  void close();
}
