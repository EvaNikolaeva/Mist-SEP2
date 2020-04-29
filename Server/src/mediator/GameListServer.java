package mediator;

import model.Game;
import model.GameList;
import model.Model;
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
  //  private UnnamedPropertyChangeSubject property;

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
}
