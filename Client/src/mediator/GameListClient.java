package mediator;

import model.Game;
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
  private RemoteGameListModel remoteGameListModel;
  private int failedConnectionCount;

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

  @Override public void registerNewUser(String username, String password)
  {
    model.registerNewUser(username, password);
  }

  @Override public User login(String username, String password)
  {
    return model.login(username, password);
  }

  @Override public ArrayList<Integer> getAllAvailableGames()
  {
    return model.getAllAvailableGames();
  }

  @Override public ArrayList<Integer> getAllPendingGames()
  {
    return model.getAllPendingGames();
  }

  @Override public ArrayList<Integer> getAllUserOwnedGames()
  {
    return model.getAllUserOwnedGames();
  }

  @Override public ArrayList<Integer> getAllUserPendingGames()
  {
    return model.getAllUserPendingGames();
  }

  @Override public ArrayList<Integer> getAllUserRentedGames()
  {
    return model.getAllUserRentedGames();
  }

  @Override public ArrayList<Integer> getAllUserIncomingGames()
  {
    return model.getAllUserIncomingGames();
  }

  @Override public void requestGame(int userID, int gameID)
      throws RemoteException
  {
    remoteGameListModel.requestGame(userID, gameID);
  }

  @Override public String getUsername(int userID) throws RemoteException
  {
    return remoteGameListModel.getUserByID(userID).getUsername();
  }

  @Override public String getBio(int userID) throws RemoteException
  {
    return remoteGameListModel.getUserByID(userID).getBio();
  }

  @Override public void removeGame(int userID, int gameID)
      throws RemoteException
  {
    remoteGameListModel.removeGame(userID, gameID);
  }

  @Override public void setBio(int userBio, String bio) throws RemoteException
  {
    remoteGameListModel.setBio(userBio, bio);
  }

  @Override public void acceptIncomingGame(int userID, int gameID)
      throws RemoteException
  {
    remoteGameListModel.acceptGame(userID, gameID);
  }

  @Override public void declineIncomingGame(int userID, int gameID)
      throws RemoteException
  {
    remoteGameListModel.declineGame(userID, gameID);
  }

  @Override public void addGame(Game game) throws RemoteException
  {
    remoteGameListModel.addGame(game.getUserID(), game.getId());
  }

  @Override public User getOtherUserByID(int userID)
  {
    return model.getOtherUserByID(userID);
  }

  @Override public ArrayList<Integer> getOtherAllUserOwnedGames(int userID)
  {
    return model.getOtherAllUserOwnedGames(userID);
  }

  @Override public ArrayList<Integer> getOtherAllUserPendingGames(int userID)
  {
    return model.getOtherAllUserPendingGames(userID);
  }

  @Override public void setLocalUserID(int userID)
  {
    model.setLocalUserID(userID);
  }
}
