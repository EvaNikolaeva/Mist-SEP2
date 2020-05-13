package model;

public class User
{
  private String username;
  private int userID;
  private String bio;
  private GameList gameList;

  public User(String username, int userID, String bio)
  {
    this.userID = userID;
    this.username = username;
    this.gameList = new GameList();
    this.bio = bio;
  }

  public int getUserID()
  {
    return userID;
  }

  public String getUsername()
  {
    return username;
  }

  public GameList getGames()
  {
    return gameList;
  }

  public String getBio()
  {
    return bio;
  }
}
