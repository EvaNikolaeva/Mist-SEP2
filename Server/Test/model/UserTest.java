package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
  private String username;
  private String password;
  private String bio;
  private int userID;
  private GameList gameList;
  private GameList rentedList;
  private User user;

  @BeforeEach void setUp()
  {
    userID = 123456;
    username = "Bob";
    password = "1111";
    bio = "zzzz";
    gameList = new GameList();
    rentedList = new GameList();
    user = new User(username, password, userID);
  }

  @Test void User()
  {
    assertNotNull(gameList);
    assertNotNull(rentedList);
    assertNotNull(user);
  }

  @Test void testUserID()
  {
    assertEquals(123456, userID);
  }

  @Test void testUsername()
  {
    assertEquals("Bob", username);
  }

  @Test void testPassword()
  {
    assertEquals("1111", password);
  }

  @Test void testBio()
  {
    assertEquals("zzzz", bio);
  }

  @Test void testAddGames()
  {

    Game game = new Game("Doom", "PC", 2016, false, 10, 123456);
    Game game1 = new Game("Warcraft 3", "PC", 2004, true, 15, 987654);
    gameList.addGame(game);
    gameList.addGame(game1);

    assertEquals(game, gameList.getGame(0));
    assertEquals(game1, gameList.getGame(1));

  }

  @Test void testRentedGames()
  {
    assertEquals(0, rentedList.size());
  }

  @Test void testGamesList()
  {
    assertEquals(0, gameList.size());
  }

  @Test void testAddToRented()
  {
    Game game = new Game("Doom", "PC", 2016, false, 10, 123456);
    rentedList.addGame(game);
    assertEquals(1, rentedList.size());
  }

  @Test void testRemoveGame()
  {
    Game game = new Game("Doom", "PC", 2016, false, 10, 123456);
    gameList.addGame(game);
    gameList.removeGame(game);
    assertEquals(0, gameList.size());
  }

  @Test void testOwns()
  {
    Game game = new Game("Doom", "PC", 2016, false, 10, 123456);
    user.addGame(game);
    assertEquals(game, user.getGameList().getGame(0));
  }
}