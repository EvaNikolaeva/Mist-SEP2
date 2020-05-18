package mediator;

import model.Game;
import model.GameAvailable;
import model.Model;
import model.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GameListClient implements GameListClientModel, Remote
{
  private Model model;
  private int failedConnectionCount;
  private ServerAccess serverAccess;

  public GameListClient(Model model)
      throws InterruptedException, RemoteException, NotBoundException,
      MalformedURLException
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
    serverWrite.registerNewUser(username, password);
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

  @Override public ArrayList<Integer> getAllAvailableGames()
      throws RemoteException
  {
    try
    {
      ServerRead serverRead = serverAccess.acquireRead();
      ArrayList<Integer> list = new ArrayList<>();
      for(int i = 0; i < serverRead.getSizeOfGameList(); i++){
        if(serverRead.getGameByIndex(i).status().equals("GameAvailable"));
        list.add(serverRead.getGameByIndex(i).getId());
      }
      return list;
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }

  @Override public ArrayList<Integer> getAllPendingGames()
      throws RemoteException
  {
    try
    {
      ServerRead serverRead = serverAccess.acquireRead();
      ArrayList<Integer> list = new ArrayList<>();
      for(int i = 0; i < serverRead.getSizeOfGameList(); i++){
        if((serverRead.getGameByIndex(i).status().equals("GamePending"))){
          list.add(serverRead.getGameByIndex(i).getId());
        }
      }
      return list;
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }

  @Override public ArrayList<Integer> getAllUserOwnedGames(int userID)
      throws RemoteException
  {
    try
    {
      ServerRead serverRead = serverAccess.acquireRead();
      return serverRead.getUserByID(userID).getOwnedGames();
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }

  @Override public ArrayList<Integer> getAllUserPendingGames(int userID)
      throws RemoteException
  {
    try
    {
      ServerRead serverRead = serverAccess.acquireRead();
      return serverRead.getUserByID(userID).getPendingGames();
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }

  @Override public ArrayList<Integer> getAllUserRentedGames(int userID)
      throws RemoteException
  {
    try
    {
      ServerRead serverRead = serverAccess.acquireRead();
      return serverRead.getUserByID(userID).getRentedGames();
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }

  @Override public ArrayList<Integer> getAllUserIncomingGames(int userID)
      throws RemoteException
  {
    try
    {
      ServerRead serverRead = serverAccess.acquireRead();
      return serverRead.getUserByID(userID).getIncomingGames();
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }

  @Override public void requestGame(int userID, int gameID)
      throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.requestGame(userID, gameID);
    serverAccess.releaseWrite();
  }

  @Override public String getUsername(int userID) throws RemoteException
  {
    try
    {
      ServerRead serverRead = serverAccess.acquireRead();
      return serverRead.getUserByID(userID).getUsername();
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }

  @Override public String getBio(int userID) throws RemoteException
  {
    try
    {
      ServerRead serverRead = serverAccess.acquireRead();
      return serverRead.getUserByID(userID).getBio();
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }

  @Override public void removeGame(int userID, int gameID)
      throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.removeGame(userID, gameID);
    serverAccess.releaseWrite();
  }

  @Override public void setBio(int userBio, String bio) throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.setBio(userBio, bio);
    serverAccess.releaseWrite();
  }

  @Override public void acceptIncomingGame(int userID, int gameID)
      throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.acceptGame(userID, gameID);
    serverAccess.releaseRead();
  }

  @Override public void declineIncomingGame(int userID, int gameID)
      throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.declineGame(userID, gameID);
    serverAccess.releaseWrite();
  }

  @Override
  public Game getGameById(int gameID) throws RemoteException {
   ServerRead serverRead = serverAccess.acquireRead();
   try{
return serverRead.getGameByID(gameID);
   }
   finally{
     serverAccess.releaseWrite();
   }
  }

  @Override public void addGame(Game game) throws RemoteException
  {
    ServerWrite serverWrite = serverAccess.acquireWrite();
    serverWrite.addGame(game.getUserID(), game);
    serverAccess.releaseWrite();
  }

  @Override public User getOtherUserByID(int userID) throws RemoteException
  {
    try
    {
      ServerRead serverRead = serverAccess.acquireRead();
      return serverRead.getUserByID(userID);
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }

  @Override public ArrayList<Integer> getOtherAllUserOwnedGames()
      throws RemoteException
  {
    try
    {
      ServerRead serverRead = serverAccess.acquireRead();
      ArrayList<Integer> list = new ArrayList<>();
      for(int i = 0; i < serverRead.getSizeOfGameList(); i++){
        if(!(serverRead.getGameByIndex(i).status().equals("GameUnavailable"))){
          list.add(serverRead.getGameByIndex(i).getId());
        }
      }
      return list;
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }

  @Override public ArrayList<Integer> getOtherAllUserPendingGames()
      throws RemoteException
  {
    try
    {
      ServerRead serverRead = serverAccess.acquireRead();
      ArrayList<Integer> list = new ArrayList<>();
      for(int i = 0; i < serverRead.getSizeOfGameList(); i++){
        if(!(serverRead.getGameByIndex(i).status().equals("GamePending"))){
          list.add(serverRead.getGameByIndex(i).getId());
        }
      }
      return list;
    }
    finally
    {
      serverAccess.releaseRead();
    }
  }
}
