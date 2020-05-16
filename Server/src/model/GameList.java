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

  public Game getGameById(int id)
  {
    return (Game) games.stream().filter(game -> id == game.getId());
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

}
