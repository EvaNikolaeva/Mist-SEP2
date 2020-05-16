package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable
{
  private String username;
  private String password;
  private int userID;
  private String bio;
  private ArrayList<Integer> ownedGames;
  private ArrayList<Integer> rentedGames;
  private ArrayList<Integer> pendingGames;
  private ArrayList<Integer> incomingGames;

  public User(String username, String password, int userID)
  {
    this.userID = userID;
    this.username = username;
    this.password = password;
    this.bio = "";
    this.ownedGames = new ArrayList<>();
    this.rentedGames = new ArrayList<>();
    this.pendingGames = new ArrayList<>();
    this.incomingGames = new ArrayList<>();
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

  public ArrayList<Integer> getOwnedGames()
  {
    return ownedGames;
  }

  public ArrayList<Integer> getRentedGames()
  {
    return rentedGames;
  }

  public ArrayList<Integer> getPendingGames()
  {
    return pendingGames;
  }

  public ArrayList<Integer> getIncomingGames()
  {
    return incomingGames;
  }
}
