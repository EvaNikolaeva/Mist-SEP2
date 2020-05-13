package model;

import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private PropertyChangeSupport property;
  private GameList list;
  private UserList userList;

  public ModelManager()
  {
    this.list = new GameList();
    this.userList = new UserList();
    this.property = new PropertyChangeSupport(this);
  }

  @Override public void AddGame(Game game)
  {
    list.addGame(game);
  }

  @Override public void RemoveGame(int id)
  {
    list.removeGame(id);
  }

  @Override public GameList GetGameList()
  {
    return list;
  }

  @Override public User getUserData(
      String username)//later should be updated to userId
  {
    return userList.getUser(username);
  }

  @Override
  public User getUserDataById(int id) {
   return userList.getUserByID(id);
  }

  @Override public void acceptTrade(Game game, int userID)
  {
    userList.getUserByID(userID).addToRentedGames(game);
    list.getGame(game.getId()).setUnavailable();
    Thread countDown = new Thread(game);
  }

  @Override public void declineTrade(Game game, int userID)
  {
    userList.getUserByID(userID).removeFromPending(game.getId());
    list.getGame(game.getId()).setAvailable();
  }

  @Override public void addToPending(Game game, int userID)
  {
    userList.getUserByID(userID).addToPending(game);
    list.getGame(game.getId()).setUnavailable();
  }

  @Override
  public void addUser(User user) {
    userList.addUser(user);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
