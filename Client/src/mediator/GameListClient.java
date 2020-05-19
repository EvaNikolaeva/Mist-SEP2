package mediator;

import model.*;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GameListClient implements GameListClientClient, RemoteGameListClient
{
  private Model model;
  private int failedConnectionCount;
  private ServerAccess serverAccess;

  public GameListClient(Model model) throws InterruptedException
  {
    this.model = model;
    this.model.setClient(this);
    this.failedConnectionCount = 0;
    connect();
  }

  @Override public void connect() throws InterruptedException
  {
    try
    {
      this.serverAccess = (ServerAccess) Naming
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

  @Override public void registerNewUser(String username, String password)
      throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.registerClient(username, password);
    serverAccess.releaseWrite();
  }

  @Override public User login(String username, String password)
      throws RemoteException
  {
    try
    {
      ServerRead serverRead = serverAccess.acquireRead();
      return serverRead.getUserByCredentials(username, password);
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }

  @Override public GameList getGamesFromServer() throws RemoteException
  {
    ServerRead serverRead = serverAccess.acquireRead();
    return serverRead.getAllGames();
  }

  @Override public void clientRemoveGame(Game game) throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.removeGame(game);
    serverAccess.releaseWrite();
  }

  @Override public void clientSetBio(User user, String bio)
      throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.setBio(user,bio);
    serverAccess.releaseWrite();
  }

  @Override public void clientAddGame(User user, Game game)
      throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.addGame(user,game);
    serverAccess.releaseWrite();
  }

  @Override public void clientRequestGame(User requester, Game game)
      throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.requestGame(requester,game);
    serverAccess.releaseWrite();
  }

  @Override public void clientAcceptIncomingGame(Rental rental)
      throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.acceptIncomingGame(rental);
    serverAccess.releaseWrite();
  }

  @Override public void clientDeclineIncomingGame(Rental rental)
      throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.declineIncomingGame(rental);
    serverAccess.releaseWrite();
  }

  @Override public User getUserFromServer(Game game) throws RemoteException
  {
    try
    {
      ServerRead server = (ServerRead) serverAccess.acquireRead();
      return server.getUser(game);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }

  @Override public void serverRemoveGame(Game game) throws RemoteException
  {

  }

  @Override public void serverAddGame(Game game) throws RemoteException
  {

  }

  @Override public void serverRequestGame(Rental rental) throws RemoteException
  {

  }

  @Override public void serverAcceptIncomingGame(Rental rental)
      throws RemoteException
  {

  }

  @Override public void serverDeclineIncomingGame(Rental rental)
      throws RemoteException
  {

  }
}
