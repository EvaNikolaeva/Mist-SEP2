package mediator;

import model.Game;
import model.GameList;
import model.Model;
import model.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameListClient implements GameListClientModel, Remote
{
  private Model model;
  private RemoteGameListModel remoteGameListModel;
  private int failedConnectionCount;

  public GameListClient(Model model)
      throws RemoteException, NotBoundException, MalformedURLException,
      InterruptedException
  {
    this.model = model;
    this.failedConnectionCount = 0;
    connect();
  }

  @Override public void connect()
      throws RemoteException, NotBoundException, MalformedURLException,
      InterruptedException
  {
    try
    {
      remoteGameListModel = (RemoteGameListModel) Naming
          .lookup("rmi://localhost:1099/games");
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (Exception e)
    {
      failedConnectionCount++;
      if (failedConnectionCount <= 5)
      {
        System.out.println(
            "Client failed to connect, attempting to connect in 5 seconds.");
        Thread.sleep(5000);
        connect();
      }
      else
      {
        System.out.println("Connection timed out, exiting.");
        System.exit(0);
      }
    }
  }

  @Override public GameList getGameList() throws RemoteException
  {
    return remoteGameListModel.getGameList();
  }

  @Override public void addGame(Game game) throws RemoteException
  {
    remoteGameListModel.addGame(game);
  }

  @Override public void removeGame(int id) throws RemoteException
  {
    remoteGameListModel.removeGame(id);
  }

  @Override public User getUserData(String username) throws RemoteException
  {
    return remoteGameListModel.getUserData(username);
  }

  @Override public void close()
  {
  }

  @Override public User getUserDataById(int id) throws RemoteException
  {
    return remoteGameListModel.getUserDataById(id);
  }

  @Override public void acceptTrade(Game game, int userID)
      throws RemoteException
  {
    remoteGameListModel.acceptTrade(game, userID);
  }

  @Override public void declineTrade(Game game, int userID)
      throws RemoteException
  {
    remoteGameListModel.declineTrade(game, userID);
  }

  @Override public void requestTrade(Game game, int targetID, int requesterID)
      throws RemoteException
  {
    remoteGameListModel.requestTrade(game, targetID, requesterID);
  }

  @Override
  public void setUserBio(User user, String bioText) throws RemoteException {
    remoteGameListModel.setUserBio(user, bioText);
  }
}
