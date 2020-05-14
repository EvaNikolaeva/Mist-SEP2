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
    return userList.getUser(
        username);      //Cata: why do we have 2 methods if it will change to id?
  }

  @Override public User getUserDataById(int id)
  {
    return userList.getUserByID(id);
  }

  @Override public void acceptTrade(Game game, int userID)
  {
    userList.getUserByID(game.getUserID()).addToRentedGames(game);
    userList.getUserByID(game.getUserID()).removeFromPending(game.getId());
    userList.getUserByID(userID).removeFromIncomingGameRequests(game.getId());
    list.getGame(game.getId()).setUnavailable();
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

  @Override public void addUser(User user)
  {
    userList.addUser(user);
  }

  @Override public void setUserBio(User user, String bioText)
  {
    userList.getUserByID(user.getUserID()).setBio(bioText);
  }

  @Override public void requestTrade(Game game, int targetID, int requesterID)
  {
    Game dummy = new Game(game.getTitle(), game.getType(),
        game.getReleaseYear(), game.deposit(), game.getRentalPeriod(),
        game.getAvailabilityPeriod(), requesterID, game.getId());

    userList.getUserByID(targetID).addToIncoming(dummy);
    userList.getUserByID(requesterID).addToPending(game);
  }

  @Override public void addToIncoming(Game game, int userID)
  {
    userList.getUserByID(userID).addToIncoming(game);
  }

  @Override public void removeFromIncoming(Game game, int userID)
  {
    userList.getUserByID(userID).removeFromIncomingGameRequests(game.getId());
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

