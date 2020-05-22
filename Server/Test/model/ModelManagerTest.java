package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ModelManagerTest
{

  private Model model;
  private Game game1;
  private Game game2;
  private GameList gameList1;
  private GameList gameList2;
  private User user1;
  private User user2;
  private UserList userList1;
  private UserList userList2;


  private ModelManager modelManager;

  @BeforeEach void setUp()
      throws RemoteException, MalformedURLException, InterruptedException,
      NotBoundException
  {
    //  model = new ModelManager();  //this is liskov, but breaks because networking
    //    modelManager = new ModelManager();
    gameList1 = new GameList();
    gameList2 = new GameList();

    userList1 = new UserList();
    userList2 = new UserList();

    user1 = new User("bob", "1234", 1234);
    user2 = new User("john", "9876", 9987);

    game1 = new Game("Doom", "PC", 2016, false, 10, 123456);
    game2 = new Game("Warcraft 3", "PC", 2004, true, 30, 987654);
  }

  @Test void ModelManager()
  {
    assertNotNull(gameList1);
    assertNotNull(gameList2);

    assertNotNull(userList1);
    assertNotNull(userList2);

    assertNotNull(user1);
    assertNotNull(user2);

    assertNotNull(game1);
    assertNotNull(game2);
  }

  @Test void addGameOne()
  {
    gameList1.addGame(game1);
    assertEquals(1, gameList1.size());
  }

  @Test void addGameNull()
  {
    gameList1.addGame(null);
    assertEquals(0, gameList1.size());
  }

  @Test void addGameMany()
  {
    gameList1.addGame(game1);
    gameList1.addGame(game2);
    assertEquals(2, gameList1.size());
  }

  @Test void addGameTwice()
  {
    gameList1.addGame(game1);
    gameList1.addGame(game1);
    assertEquals(1, gameList1.size());
  }

  @Test void removeGameEmptyList()
  {
    int value = game1.getId();
    gameList1.removeGame(value);
    assertEquals(0, gameList1.size());
  }

  @Test void removeGameOne()
  {
    int value = game1.getId();
    gameList1.addGame(game1);
    gameList1.removeGame(value);
    assertEquals(0, gameList1.size());
  }

  @Test void removeGameMany()
  {
    int value = game1.getId();
    int value1 = game2.getId();
    gameList1.addGame(game1);
    gameList1.addGame(game2);
    gameList1.removeGame(value);
    gameList1.removeGame(value1);
    assertEquals(0, gameList1.size());
  }

  @Test void removeGameTwice()
  {
    int value = game1.getId();
    gameList1.addGame(game1);
    gameList1.removeGame(value);
    gameList1.removeGame(value);
    assertEquals(0, gameList1.size());
  }

  @Test void removeGameFromMultipleOnes()
  {
    int value = game1.getId();
    gameList1.addGame(game1);
    gameList1.addGame(game2);
    gameList1.removeGame(value);
    assertEquals(1, gameList1.size());
  }

  @Test void getGameList() throws RemoteException
  {
    gameList1.addGame(game1);
    gameList1.addGame(game2);

    gameList2.addGame(game1);
    gameList2.addGame(game2);

    assertEquals(gameList1, gameList2);
  }

  @Test void testGetUserByID()
  {

  }

}