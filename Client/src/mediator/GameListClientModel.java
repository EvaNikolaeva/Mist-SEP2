package mediator;

import model.Game;
import model.GameList;
import model.User;

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
  User getUserData(String username) throws RemoteException;
  void close();
  User getUserDataById(int id) throws RemoteException;
  void acceptTrade(Game game,int userID) throws RemoteException;
  void declineTrade(Game game,int userID) throws RemoteException;
  void requestTrade(Game game, int userID) throws RemoteException;
  void setUserBio(User user, String bioText) throws RemoteException;
}
