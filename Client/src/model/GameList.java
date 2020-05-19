package model;

import java.io.Serializable;
import java.util.ArrayList;

public class GameList implements Serializable
{
  private ArrayList<Game> games;

  public GameList()
  {
    this.games = new ArrayList<>();
  }

  public void addGame(Game game)
  {
    if (game == null)
    {
      return;
    }
    for (int i = 0; i < games.size(); i++)
    {
      if (games.get(i).equals(game))
      {
        return;
      }
    }
    games.add(game);
  }

  public void removeGame(int id)
  {
    for (int i = 0; i < games.size(); i++)
    {
      if (games.get(i).getId() == id)
        games.remove(games.get(i));
    }
  }

  public void removeGame(Game game)
  {
    games.remove(game);
  }

  public Game getGameById(int id)
  {
    return games.stream().filter(game -> id == game.getId()).findFirst().orElse(null);
  }

  public Game getGame(int index)
  {
    if (index < 0 || index > games.size())
      return null;
    return games.get(index);
  }

  public int size()
  {
    return games.size();
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof GameList))
      return false;
    GameList other = (GameList) obj;
    return games.equals(other.games);
  }

  public ArrayList<Game> getAvailableGames()
  {
    ArrayList<Game> dummy = new ArrayList<>();
    for(int i = 0;i<games.size();i++)
    {
      if(games.get(i).getAvailable())
        dummy.add(games.get(i));
    }
    return dummy;
  }

  public ArrayList<Game> getUnavailableGames()
  {
    ArrayList<Game> dummy = new ArrayList<>();
    for(int i = 0;i<games.size();i++)
    {
      if(!(games.get(i).getAvailable()))
        dummy.add(games.get(i));
    }
    return dummy;
  }

}
