//package model;
//
//import mediator.GameListClient;
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
//  private User user1;
//  private User user2;
//  private GameListClient gameListClient;
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
////  model = new ModelManager();  //this is liskov, but breaks because networking
//    user1 = new User("Bob", 123456);
//    user2 = new User("Joe", 987654);
////    modelManager = new ModelManager();
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
//    assertNotNull(user1);
//    assertNotNull(user2);
//  }
//
//  @Test void addGameOne()
//  {
//    user1.getGames().addGame(game1);
//    assertEquals(1, user1.getGames().size());
//  }
//
//  @Test void addGameNull()
//  {
//    user1.getGames().addGame(null);
//    assertEquals(0, user1.getGames().size());
//  }
//
//  @Test void addGameMany()
//  {
//    user1.getGames().addGame(game1);
//    user1.getGames().addGame(game2);
//    assertEquals(2, user1.getGames().size());
//  }
//
//  @Test void addGameTwice()
//  {
//    user1.getGames().addGame(game1);
//    user1.getGames().addGame(game1);
//    assertEquals(1, user1.getGames().size());
//  }
//
//  @Test void removeGameEmptyList()
//  {
//    int value = game1.getId();
//    user1.getGames().removeGame(value);
//    assertEquals(0, user1.getGames().size());
//  }
//
//  @Test void removeGameOne()
//  {
//    int value = game1.getId();
//    user1.getGames().addGame(game1);
//    user1.getGames().removeGame(value);
//    assertEquals(0, user1.getGames().size());
//  }
//
//  @Test void removeGameMany()
//  {
//    int value = game1.getId();
//    int value1 = game2.getId();
//    user1.getGames().addGame(game1);
//    user1.getGames().addGame(game2);
//    user1.getGames().removeGame(value);
//    user1.getGames().removeGame(value1);
//    assertEquals(0, user1.getGames().size());
//  }
//
//  @Test void removeGameTwice()
//  {
//    int value = game1.getId();
//    user1.getGames().addGame(game1);
//    user1.getGames().removeGame(value);
//    user1.getGames().removeGame(value);
//    assertEquals(0, user1.getGames().size());
//  }
//
//  @Test void removeGameFromMultipleOnes()
//  {
//    int value = game1.getId();
//    user1.getGames().addGame(game1);
//    user1.getGames().addGame(game2);
//    user1.getGames().removeGame(value);
//    assertEquals(1, user1.getGames().size());
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
//  @Test void getUserGamesList()
//  {
//    user1.getGames().addGame(game1);
//    user1.getGames().addGame(game2);
//
//    user2.getGames().addGame(game1);
//    user2.getGames().addGame(game2);
//
//    assertEquals(user1.getGames(), user1.getGames());
//  }
//
//  @Test void updateUserGames() throws RemoteException
//  {
//    gameList1.addGame(game1);
//    assertEquals(user1.getUserID(), gameList1.getGame(0).getUserID());
//  }
//
//  @Test void getUserId()
//  {
//    assertEquals(123456, game1.getUserID());
//  }
//
//  @Test void validateGame()
//  {
//    assertNotNull(game1.getTitle());
//    assertNotNull(game1.getType());
//    assertNotNull(game1.getReleaseYear());
//    assertNotNull(game1.getRentalPeriod());
//    assertNotNull(game1.getAvailabilityPeriod());
//
//    assertNotEquals("", game1.getReleaseYear());
//    assertNotEquals("", game1.getAvailabilityPeriod());
//
//    assertNotEquals(date1, date2);
//    assertTrue(date1.getYear() < date2.getYear() &&
//        date1.getMonthValue() < date2.getMonthValue() && date1.getYear() < date2.getYear());
//
//    assertNotEquals("", game1.getTitle());
//    assertNotEquals("", game1.getType());
//    assertTrue(game1.getReleaseYear() > 0);
//    assertTrue(game1.getAvailabilityPeriod() > 1);
//  }
//
//  @Test void validateGame1()
//  {
//    assertNull(game1.getTitle());
//    assertNotNull(game1.getType());
//    assertNotNull(game1.getReleaseYear());
//    assertNotNull(game1.getRentalPeriod());
//    assertNotNull(game1.getAvailabilityPeriod());
//
//    assertNotEquals("", game1.getReleaseYear());
//    assertNotEquals("", game1.getAvailabilityPeriod());
//
//    assertNotEquals(date1, date2);
//    assertTrue(date1.getYear() < date2.getYear() &&
//        date1.getMonthValue() < date2.getMonthValue() && date1.getYear() < date2.getYear());
//
//    assertNotEquals("", game1.getTitle());
//    assertNotEquals("", game1.getType());
//    assertTrue(game1.getReleaseYear() > 0);
//    assertTrue(game1.getAvailabilityPeriod() > 1);
//  }
//
//}