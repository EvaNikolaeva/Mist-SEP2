package mediator;

import model.*;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameListClient implements GameListClientClient, Remote
{

  private int failedConnectionCount;
  private GameListServerModel server;

  public GameListClient() throws InterruptedException
  {
    this.failedConnectionCount = 0;
    connect();
  }

  @Override public void connect() throws InterruptedException
  {
    try
    {
//      this.serverAccess = (ServerAccess) Naming
//          .lookup("rmi://localhost:1099/games");
//      UnicastRemoteObject.exportObject(this, 0);
      this.server = (GameListServerModel) Naming.lookup("rmi://localhost:1099/games");
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (Exception e)
    {
      failedConnectionCount++;
      if (failedConnectionCount <= 5)
      {
        System.out.println(
            "Client failed to connect, attempting to connect in 5 seconds.");
        System.out.println(e);
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
   server.registerClient(username, password);
  }

  @Override public User login(String username, String password)
      throws RemoteException
  {
return server.getUserByCredentials(username, password);
  }

  @Override public GameList getGamesFromServer() throws RemoteException
  {
   return server.getAllGames();
  }

  @Override
  public RentalList clientGetRentalList() throws RemoteException {
  return server.getRentalList();
  }

  @Override public void clientRemoveGame(Game game) throws RemoteException
  {
 server.removeGame(game);
  }

  @Override public void clientSetBio(User user, String bio)
      throws RemoteException
  {
  server.setBio(user, bio);
  }

  @Override public void clientAddGame(Game game)
      throws RemoteException
  {
server.addGame(game);
  }

  @Override public void clientRequestGame(User requester, Game game)
      throws RemoteException
  {
server.requestGame(requester, game);
  }

  @Override public void clientAcceptIncomingGame(Rental rental)
      throws RemoteException
  {
  server.acceptIncomingGame(rental);
  }

  @Override public void clientDeclineIncomingGame(Rental rental)
      throws RemoteException
  {
server.declineIncomingGame(rental);
  }

  @Override public User getUserFromServer(Game game) throws RemoteException
  {
    return server.getUserByGame(game);
  }

  @Override
  public void setGameAvailableTrue(Game game) throws RemoteException {
    server.setGameAvailableTrue(game);
  }

//  @Override
//  public boolean addListener(GeneralListener<GameList, RentalList> listener, String... propertyNames) throws RemoteException {
//    return false;
//  }
//
//  @Override
//  public boolean removeListener(GeneralListener<GameList, RentalList> listener, String... propertyNames) throws RemoteException {
//    return false;
//  }
}
