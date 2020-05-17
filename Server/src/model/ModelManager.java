package model;

import mediator.GameListServer;
import mediator.RemoteGameListModel;
import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class ModelManager implements Model
{
  private PropertyChangeSupport propertyChangeSupport;
  private GameList gameList;
  private UserList userList;
  private RemoteGameListModel remoteGameListModel;

  public ModelManager()
  {
    this.gameList = new GameList();
    this.userList = new UserList();
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.remoteGameListModel = new GameListServer(this);
  }

  @Override public User getUserByID(int id)
  {
    return userList.getUserByUserID(id);
  }

  @Override public User getUserByCredentials(String username, String password)
  {
    return userList.getUserByCredentials(username, password);
  }

  @Override public void setBio(int userID, String bio) throws RemoteException
  {
    userList.getUserByUserID(userID).setBio(bio);
    remoteGameListModel.setBio(userID, bio);
  }

  @Override public void requestGame(int userID, int gameID)
      throws RemoteException
  {

  }

  @Override public void acceptGame(int userID, int gameID)
      throws RemoteException
  {

  }

  @Override public void declineGame(int userID, int gameID)
      throws RemoteException
  {
    
  }

  @Override public void addGame(int userID, int gameID) throws RemoteException
  {
    userList.getUserByUserID(userID).getOwnedGames().add(gameID);
    remoteGameListModel.addGame(userID, gameID);
  }

  @Override public void removeGame(int userID, int gameID)
      throws RemoteException
  {
    userList.getUserByUserID(userID).getOwnedGames().remove(gameID);
    remoteGameListModel.removeGame(userID, gameID);
  }

  @Override public Game getGameByIndex(int index)
  {
    return gameList.getGame(index);
  }

  @Override public Game getGameByID(int gameID)
  {
    return gameList.getGameById(gameID);
  }

  @Override public int getSizeOfGameList()
  {
    return gameList.size();
  }

  @Override public void registerUser(String username, String password)
      throws RemoteException
  {
    userList.registerUser(username, password);
    remoteGameListModel.registerUser(username, password);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
}

