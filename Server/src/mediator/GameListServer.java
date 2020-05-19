package mediator;

import javafx.application.Platform;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GameListServer
    implements ServerWrite, PropertyChangeListener
{
  private Model model;
  private ArrayList<RemoteGameListClient> remoteGameListClients;

  public GameListServer(Model model)
  {
    this.model = model;
    this.remoteGameListClients = new ArrayList<>();
    startServer();
  }

  private void startServer()
  {
    try
    {
      startRegistry();
      ServerAccess stub = (ServerAccess) UnicastRemoteObject
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



  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())
    {
      case "addGame":
        for (int i = 0; i < remoteGameListClients.size(); i++)
        {
          try
          {
            remoteGameListClients.get(i).serverAddGame((Game) evt.getNewValue());
          }
          catch (RemoteException e)
          {
            e.printStackTrace();
          }
        }
        break;
      case "removeGame":
        for (int i = 0; i < remoteGameListClients.size(); i++)
        {
          try
          {
            remoteGameListClients.get(i).serverRemoveGame((Game) evt.getNewValue());
          }
          catch (RemoteException e)
          {
            e.printStackTrace();
          }
        }
        break;
      case "acceptGame":
        for (int i = 0; i < remoteGameListClients.size(); i++)
        {
          try
          {
            remoteGameListClients.get(i).serverAcceptIncomingGame((Rental) evt.getNewValue());
          }
          catch (RemoteException e)
          {
            e.printStackTrace();
          }
        }
        break;
      case "declineGame":
        for (int i = 0; i < remoteGameListClients.size(); i++)
        {
          try
          {
            remoteGameListClients.get(i).serverDeclineIncomingGame((Rental) evt.getNewValue());
          }
          catch (RemoteException e)
          {
            e.printStackTrace();
          }
        }
        break;

      case "newRental": // trebuie gandita sa trimita numai la cei implicanti in rental... sugerez cautand username pana in model si returanand
        for (int i = 0; i < remoteGameListClients.size(); i++)
        {
          try
          {
            remoteGameListClients.get(i).serverRequestGame((Rental) evt.getNewValue());
          }
          catch (RemoteException e)
          {
            e.printStackTrace();
          }
        }
        break;
    }
  }

  @Override public void addClient(RemoteGameListClient client)
      throws RemoteException
  {
    this.remoteGameListClients.add(client);
  }

  @Override public void registerClient(String username, String password)
      throws RemoteException
  {
    this.model.registerUser(username,password);
  }

  @Override public User loginClient(String username, String password)
      throws RemoteException
  {
    return this.model.getUserByCredentials(username,password);
  }

  @Override public void removeGame(Game game) throws RemoteException
  {
    this.model.removeGame(game);
  }

  @Override public void setBio(User user, String bio) throws RemoteException
  {
    this.model.setBio(user,bio);
  }

  @Override public void addGame(User user, Game game) throws RemoteException
  {
    this.model.addGame(user,game);
  }

  @Override public void requestGame(User requester,Game game) throws RemoteException
  {
    this.model.requestGame(requester, game);
  }

  @Override public void acceptIncomingGame(Rental rental) throws RemoteException
  {
    this.model.acceptGame(rental);
  }

  @Override public void declineIncomingGame(Rental rental)
      throws RemoteException
  {
    this.model.declineGame(rental);
  }

  @Override public GameList getAllGames() throws RemoteException
  {
    return this.model.getAllGames();
  }

  @Override public User getUser(Game game) throws RemoteException
  {
    return this.model.getUserByGame(game);
  }

  @Override public User getUserByCredentials(String username, String password)
      throws RemoteException
  {
    return this.model.getUserByCredentials(username, password);
  }
}
