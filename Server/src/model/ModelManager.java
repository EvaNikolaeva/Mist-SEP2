package model;

import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private PropertyChangeSupport propertyChangeSupport;
  private GameList gameList;
  private UserList userList;

  public ModelManager()
  {
    this.gameList = new GameList();
    this.userList = new UserList();
    this.propertyChangeSupport = new PropertyChangeSupport(this);
  }

  @Override public User getUserByID(int id)
  {
    return userList.getUserByUserID(id);
  }

  @Override public User getUserByCredentials(String username, String password)
  {
    return userList.getUserByCredentials(username, password);
  }

  @Override public void setBio(int userID, String bio)
  {
    userList.getUserByUserID(userID).setBio(bio);
  }

  @Override public void requestGame(int userID, int gameID)
  {

  }

  @Override public void acceptGame(int userID, int gameID)
  {

  }

  @Override public void declineGame(int userID, int gameID)
  {

  }

  @Override public void addGame(int userID, int gameID)
  {
    userList.getUserByUserID(userID).getOwnedGames().add(gameID);
  }

  @Override public void removeGame(int userID, int gameID)
  {
    userList.getUserByUserID(userID).getOwnedGames().remove(gameID);
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
  {
    userList.registerUser(username, password);
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

