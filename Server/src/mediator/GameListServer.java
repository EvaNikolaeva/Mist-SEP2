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

public class GameListServer implements RemoteGameListModel, ServerWrite
{
  private Model model;
  //arrays users

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

  @Override public User getUserByID(int id)
  {

    return model.getUserByID(id);
  }

  @Override public User getUserByCredentials(String username, String password)
  {
    return model.getUserByCredentials(username, password);
  }

  @Override public void setBio(int userID, String bio) throws RemoteException
  {
    model.setBio(userID, bio);
  }

  @Override public void requestGame(int userID, int gameID)
      throws RemoteException
  {
    model.requestGame(userID, gameID);
  }

  @Override public void acceptGame(int userID, int gameID)
      throws RemoteException
  {
    model.acceptGame(userID, gameID);
  }

  @Override public void declineGame(int userID, int gameID)
      throws RemoteException
  {
    model.declineGame(userID, gameID);
  }

  @Override public void addGame(int userID, Game game) throws RemoteException
  {
    model.addGame(userID, game);
  }

  @Override public void removeGame(int userID, int gameID)
      throws RemoteException
  {
    model.removeGame(userID, gameID);
  }

  @Override public Game getGameByIndex(int index)
  {
    return model.getGameByIndex(index);
  }

  @Override public Game getGameByID(int gameID)
  {
    return model.getGameByID(gameID);
  }

  @Override public int getSizeOfGameList()
  {
    return model.getSizeOfGameList();
  }

  @Override public void registerNewUser(String username, String password)
      throws RemoteException
  {
    model.registerUser(username, password);
  }
}
