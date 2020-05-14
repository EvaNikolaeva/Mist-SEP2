package mediator;

import model.Game;
import model.GameList;
import model.Model;
import model.User;
import utility.UnnamedPropertyChangeSubject;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class GameListServer implements RemoteGameListModel
{
  private Model model;

  public GameListServer(Model model)
  {
    this.model = model;
    startServer();
  }

  private void startServer()
  {
    try
    {
      startRegistry();
      RemoteGameListModel stub = (RemoteGameListModel) UnicastRemoteObject
          .exportObject(this, 1099);
      Naming.rebind("games", stub);
      System.out.println("Starting server...");
      Registry registry = LocateRegistry.getRegistry();
    }
    catch (RemoteException | MalformedURLException e)
    {
      e.printStackTrace();
    }
  }

  private void startRegistry()
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);

      System.out.println("Registry started...");
    }
    catch (java.rmi.server.ExportException ex)
    {
      System.out.println("Registry already started? " + ex.getMessage());
    }
    catch (RemoteException ex)
    {
      System.out.println("Error: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  @Override public GameList getGameList()
  {
    return model.GetGameList();
  }

  @Override public void addGame(Game game)
  {
    model.AddGame(game);
  }

  @Override public void removeGame(int id)
  {
    model.RemoveGame(id);
  }

  @Override public User getUserData(String username) throws RemoteException
  {
    return model.getUserData(
        username);     //this is never used, was changed with getUser(String username)
  }

  @Override public User getUserDataById(int id) throws RemoteException
  {
    return model.getUserDataById(id);
  }

  @Override public void acceptTrade(Game game, int userID)
      throws RemoteException
  {
    model.acceptTrade(game, userID);
  }

  @Override public void declineTrade(Game game, int userID)
      throws RemoteException
  {
    model.declineTrade(game, userID);
  }

  @Override public void requestTrade(Game game, int userID)
      throws RemoteException
  {
    model.addToPending(game, userID);
  }

  @Override
  public void setUserBio(User user, String bioText) throws RemoteException {
    model.setUserBio(user, bioText);
  }
}
