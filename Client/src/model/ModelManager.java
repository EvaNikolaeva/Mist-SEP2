package model;

import mediator.GameListClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ModelManager implements Model
{
  private GameListClient client;
  private User user;
  private PropertyChangeSupport property;

  public ModelManager()
      throws RemoteException, MalformedURLException, NotBoundException,
      InterruptedException
  {
    this.client = new GameListClient(this);
    this.user = new User("Testy", 123456);
    this.property = new PropertyChangeSupport(this);
    updateUserGames();
  }


  @Override public void AddGame(Game game) throws RemoteException
  {

      for(int i = 0;i<client.getGameList().size();i++)
      {
        if(game.getId() == client.getGameList().getGame(i).getId())
        {
          game.reRollID();
        }
        else
        {
          client.addGame(game);
          user.getGames().addGame(game);
        }
      }
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

  @Override public void updateUserGames() throws RemoteException
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

  @Override public int getUserId()
  {
    return user.getUserID();
  }

}
