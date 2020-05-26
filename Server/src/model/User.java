package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing the User of the system.
 *
 * @author Group 1
 */
public class User implements Serializable
{
  private String username;
  private String password;
  private int userID;
  private String bio;
  private GameList gameList;
  private GameList rentedList;

  /**
   * Three argument constructor initialising the variables.
   *
   * @param username The username of the user,
   * @param password The password for the profile.
   * @param userID   The ID of the user to be indentyfied by.
   */
  public User(String username, String password, int userID)
  {
    this.userID = userID;
    this.username = username;
    this.password = password;
    this.bio = "";
    this.gameList = new GameList();
    this.rentedList = new GameList();
  }

  /**
   * Getting the ID of the user.
   *
   * @return Integer representing the ID of the User.
   */
  public int getUserID()
  {
    return userID;
  }

  /**
   * Getting the username of the User.
   *
   * @return A String representing the username of the User.
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * Getting the password of the User profile.
   *
   * @return A String representing the password of the User.
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Getting the bio of the User.
   *
   * @return A String representing the bio of the User.
   */
  public String getBio()
  {
    return bio;
  }

  /**
   * Setting the bio of the User.
   *
   * @param bio A string representing the bio of the User.
   */
  public void setBio(String bio)
  {
    if (bio != null)
      this.bio = bio;
  }

  /**
   * Getting the list of Games the User rented.
   *
   * @return A GameList representing the Games the User has rented.
   */
  public GameList getRentedGameList()
  {
    return rentedList;
  }

  /**
   * Adding to the list of the Games the User has rented.
   *
   * @param game The Game to be added to the list of games the User has rented.
   */
  public void addToRented(Game game)
  {
    rentedList.addGame(game);
  }

  /**
   * Adding a Game to the list of games the User owns.
   *
   * @param game The Game to be added to the list.
   */
  public void addGame(Game game)
  {
    gameList.addGame(game);
  }

  /**
   * Removing a Game from the owned games.
   *
   * @param game The Game to be removed.
   */
  public void removeGame(Game game)
  {
    gameList.removeGame(game);
  }

  /**
   * Returning the Games the User owns.
   *
   * @return An ArrayList of Games the User has.
   */
  public GameList getGameList()
  {
    return gameList;
  }

  /**
   * Returning whether the User has the given Game.
   *
   * @param game The Game to be checked.
   * @return A boolean, whether the User has it.
   */
  public boolean ownsGame(Game game)
  {
    for (int i = 0; i < gameList.size(); i++)
    {
      if (gameList.getGame(i).equals(game))
        return true;
    }
    return false;
  }
}
