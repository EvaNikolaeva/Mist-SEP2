package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable
{
  private String username;
  private String password;
  private int userID;
  private String bio;
  private GameList gameList;

  public User(String username, String password, int userID)
  {
    this.userID = userID;
    this.username = username;
    this.password = password;
    this.bio = "";
    this.gameList = new GameList();
  }

  public int getUserID()
  {
    return userID;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public String getBio()
  {
    return bio;
  }

  public void setBio(String bio)
  {
    this.bio = bio;
  }

  public void addGame(Game game)
  {
    gameList.addGame(game);
  }

  public void removeGame(Game game)
  {
    gameList.removeGame(game);
  }

  public GameList getGameList()
  {
    return gameList;
  }

  public boolean ownsGame(Game game)
  {
    for (int i = 0; i < gameList.size(); i++)
    {
      if(gameList.getGame(i).equals(game))
        return true;
    }
    return false;
  }
}
