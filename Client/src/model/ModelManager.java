package model;

import mediator.GameListClient;

import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class ModelManager implements Model
{
  private GameListClient client;
  private User user;

  public ModelManager() throws RemoteException
  {
    this.client = new GameListClient(this);
    this.user = new User("Testy", 123456);
  }

  @Override public void AddGame(Game game) throws RemoteException
  {
    client.addGame(game);
    user.getGames().addGame(game);
  }

  @Override public void RemoveGame(int id) throws RemoteException
  {
    client.removeGame(id);
    user.getGames().removeGame(id);
  }

  @Override public GameList GetGameList() throws RemoteException
  {
    return client.getGameList();
  }

  @Override public GameList getUserGamesList()
  {
    return user.getGames();
  }

  public void updateUserGames() throws RemoteException
  {
    GameList gameList = GetGameList();
    for (int i = 0; i < gameList.size(); i++)
    {
      if (gameList.getGame(i).getUserID() == user.getUserID())
      {
        user.getGames().addGame(gameList.getGame(i));
      }
    }
  }
}
