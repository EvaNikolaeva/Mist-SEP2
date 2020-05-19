//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserTest
//{
//  private String username;
//  private int userID;
//  private GameList gameList;
//
//  @BeforeEach void setUp()
//  {
//    userID = 123456;
//    username = "Bob";
//    gameList = new GameList();
//  }
//
//  @Test void getUserID()
//  {
//    assertEquals(123456, userID);
//  }
//
//  @Test void getUsername()
//  {
//    assertEquals("Bob", username);
//  }
//
//  @Test void getGames()
//  {
//    LocalDate date1 = LocalDate.of(2020, 4, 12);
//    LocalDate date2 = LocalDate.of(2020, 4, 20);
//    DateInterval interval1 = new DateInterval(date1, date2);
//
//    LocalDate date3 = LocalDate.of(2020, 3, 12);
//    LocalDate date4 = LocalDate.of(2020, 3, 20);
//    DateInterval interval2 = new DateInterval(date3, date4);
//
//    Game game = new Game("Doom", "PC", 2016, false,
//        interval1, 10, 123456);
//    Game game1 = new Game("Warcraft 3", "PC", 2004, true,
//        interval2, 15, 987654);
//    gameList.addGame(game);
//    gameList.addGame(game1);
//
//    assertEquals(game, gameList.getGame(0));
//    assertEquals(game1, gameList.getGame(1));
//
//  }
//}