package model;

import mediator.GameListClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ModelManagerTest
{
  private User user1;
  private User user2;
  private String username1;
  private String username2;
  private String password1;
  private String password2;
  private Game game1;
  private Game game2;
  private Game gameBuffer;
  private GameList gameList1;
  private GameList gameList2;

  //Not everything can be tested because of networking. And most of the methods are call outs to the other classes.

  @BeforeEach void setUp()
  {
    username1 = "abc";
    username2 = "xyz";

    password1 = "123";
    password2 = "987";

    user1 = new User("bob", "1234", 1234);
    user2 = new User("Joe", "9876", 9876);

    gameList1 = new GameList();
    gameList2 = new GameList();

    gameBuffer = null;
    game1 = new Game("Doom", "PC", 2016, false, 10, 1234);
    game2 = new Game("Warcraft 3", "PC", 2004, true, 30, 9876);
  }

  @Test void ModelManager()
  {
    assertNotNull(username1);
    assertNotNull(username2);
    assertNotNull(password1);
    assertNotNull(password2);
    assertNotNull(user1);
    assertNotNull(user2);
    assertNotNull(game1);
    assertNotNull(game2);
    assertNotNull(gameList1);
    assertNotNull(gameList2);
    assertNull(gameBuffer);
  }

  @Test void addGameOne()
  {
    user1.addGame(game2);
    assertEquals(1, user1.getGameList().size());
  }

  @Test void addGameNull()
  {
    user1.addGame(null);
    assertEquals(0, user1.getGameList().size());
  }

  @Test void addGameMany()
  {
    user1.addGame(game1);
    user1.addGame(game2);
    assertEquals(2, user1.getGameList().size());
  }

  @Test void addGameTwice()
  {
    user1.addGame(game1);
    user1.addGame(game1);
    assertEquals(1, user1.getGameList().size());
  }

  @Test void removeGameEmptyList()
  {
    user1.removeGame(game1);
    assertEquals(0, user1.getGameList().size());
  }

  @Test void removeGameOne()
  {
    user1.addGame(game1);
    user1.removeGame(game1);
    assertEquals(0, user1.getGameList().size());
  }

  @Test void removeGameMany()
  {

    user1.addGame(game1);
    user1.addGame(game2);
    user1.removeGame(game1);
    user1.removeGame(game2);
    assertEquals(0, user1.getGameList().size());
  }

  @Test void removeGameTwice()
  {

    user1.addGame(game1);
    user1.removeGame(game1);
    user1.removeGame(game1);
    assertEquals(0, user1.getGameList().size());
  }

  @Test void removeGameFromMultipleOnes()
  {
    user1.addGame(game1);
    user1.addGame(game2);
    user1.removeGame(game1);
    assertEquals(1, user1.getGameList().size());
  }

  @Test void getGameList() throws RemoteException
  {
    gameList1.addGame(game1);
    gameList1.addGame(game2);

    gameList2.addGame(game1);
    gameList2.addGame(game2);

    assertEquals(gameList1, gameList2);
  }

  @Test void getUserGamesList()
  {
    user1.addGame(game1);
    user1.addGame(game2);

    user2.addGame(game1);
    user2.addGame(game2);

    assertEquals(user1.getGameList(), user1.getGameList());
  }

  @Test void updateUserGames() throws RemoteException
  {
    gameList1.addGame(game1);
    assertEquals(user1.getUserID(), gameList1.getGame(0).getUserId());
  }

  @Test void getUserId()
  {
    assertEquals(1234, game1.getUserId());
  }

  @Test void validateGame()
  {
    assertNotNull(game1.getTitle());
    assertNotNull(game1.getType());
    assertNotNull(game1.getReleaseYear());
    assertNotNull(game1.getAvailabilityPeriod());

    assertNotEquals("", game1.getReleaseYear());
    assertNotEquals("", game1.getAvailabilityPeriod());

    assertNotEquals("", game1.getTitle());
    assertNotEquals("", game1.getType());
    assertTrue(game1.getReleaseYear() > 0);
    assertTrue(game1.getAvailabilityPeriod() > 1);
  }
}