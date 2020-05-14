package model;

import java.io.Serializable;

public class User implements Serializable
{
  private String username;
  private int userID;
  private String bio;
  private GameList gameList;
  private GameList pendingGames;
  private GameList currentlyRentedGames;
  private GameList incomingGameRequests;

  public User(String username, int userID)
  {
    this.userID = userID;
    this.username = username;
    this.gameList = new GameList();
    this.pendingGames = new GameList();
    this.incomingGameRequests = new GameList();
    this.currentlyRentedGames = new GameList();
    this.bio = "";
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

  public void setBio(String bio)
  {
    this.bio = bio;
  }

  public GameList getPendingGames()
  {
    return pendingGames;
  }

  public void addToPending(Game game)
  {
    pendingGames.addGame(game);
  }

  public GameList getIncomingGameRequests(){
    return incomingGameRequests;
  }
  public void removeFromIncomingGameRequests(int gameId){
    incomingGameRequests.removeGame(gameId);
  }

  public void removeFromPending(int gameId)
  {
    pendingGames.removeGame(gameId);
  }

  public GameList getRentedGames()
  {
    return currentlyRentedGames;
  }

  public void addToRentedGames(Game game)
  {
    currentlyRentedGames.addGame(game);
  }

  public void removeFromRentedGames(int gameId)
  {
    currentlyRentedGames.removeGame(gameId);
  }
}
