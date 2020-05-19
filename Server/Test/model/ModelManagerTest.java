//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.net.MalformedURLException;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ModelManagerTest
//{
//
//  private Model model;
//  private Game game1;
//  private Game game2;
//  private GameList gameList1;
//  private GameList gameList2;
//  private LocalDate date1;
//  private LocalDate date2;
//  private LocalDate date3;
//  private LocalDate date4;
//  private DateInterval dateInterval1;
//  public DateInterval dateInterval2;
//  private ModelManager modelManager;
//
//  @BeforeEach void setUp()
//      throws RemoteException, MalformedURLException, InterruptedException,
//      NotBoundException
//  {
//    //  model = new ModelManager();  //this is liskov, but breaks because networking
//    //    modelManager = new ModelManager();
//    gameList1 = new GameList();
//    gameList2 = new GameList();
//
//    date1 = LocalDate.of(2020, 4, 12);
//    date2 = LocalDate.of(2020, 4, 20);
//    dateInterval1 = new DateInterval(date1, date2);
//    game1 = new Game("Doom", "PC", 2016, false,
//        dateInterval1, 10, 123456);
//
//    date3 = LocalDate.of(2020, 3, 12);
//    date4 = LocalDate.of(2020, 3, 20);
//    dateInterval2 = new DateInterval(date3, date4);
//    game2 = new Game("Warcraft 3", "PC", 2004, true,
//        dateInterval2, 30, 987654);
//  }
//
//  @Test void ModelManager()
//  {
//    assertNotNull(gameList1);
//    assertNotNull(gameList2);
//  }
//
//  @Test void addGameOne()
//  {
//    gameList1.addGame(game1);
//    assertEquals(1, gameList1.size());
//  }
//
//  @Test void addGameNull()
//  {
//    gameList1.addGame(null);
//    assertEquals(0, gameList1.size());
//  }
//
//  @Test void addGameMany()
//  {
//    gameList1.addGame(game1);
//    gameList1.addGame(game2);
//    assertEquals(2, gameList1.size());
//  }
//
//  @Test void addGameTwice()
//  {
//    gameList1.addGame(game1);
//    gameList1.addGame(game1);
//    assertEquals(1, gameList1.size());
//  }
//
//  @Test void removeGameEmptyList()
//  {
//    int value = game1.getId();
//    gameList1.removeGame(value);
//    assertEquals(0, gameList1.size());
//  }
//
//  @Test void removeGameOne()
//  {
//    int value = game1.getId();
//    gameList1.addGame(game1);
//    gameList1.removeGame(value);
//    assertEquals(0, gameList1.size());
//  }
//
//  @Test void removeGameMany()
//  {
//    int value = game1.getId();
//    int value1 = game2.getId();
//    gameList1.addGame(game1);
//    gameList1.addGame(game2);
//    gameList1.removeGame(value);
//    gameList1.removeGame(value1);
//    assertEquals(0, gameList1.size());
//  }
//
//  @Test void removeGameTwice()
//  {
//    int value = game1.getId();
//    gameList1.addGame(game1);
//    gameList1.removeGame(value);
//    gameList1.removeGame(value);
//    assertEquals(0, gameList1.size());
//  }
//
//  @Test void removeGameFromMultipleOnes()
//  {
//    int value = game1.getId();
//    gameList1.addGame(game1);
//    gameList1.addGame(game2);
//    gameList1.removeGame(value);
//    assertEquals(1, gameList1.size());
//  }
//
//  @Test void getGameList() throws RemoteException
//  {
//    gameList1.addGame(game1);
//    gameList1.addGame(game2);
//
//    gameList2.addGame(game1);
//    gameList2.addGame(game2);
//
//    assertEquals(gameList1, gameList2);
//  }
//
//
//}