//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class GameTest
//{
//  private Game game;
//  private LocalDate date1;
//  private LocalDate date2;
//  private DateInterval dateInterval;
//
//  @BeforeEach void setUp()
//  {
//    date1 = LocalDate.of(2020, 4, 12);
//    date2 = LocalDate.of(2020, 4, 20);
//    dateInterval = new DateInterval(date1, date2);
//    game = new Game("Doom", "PC", 2016, false,
//        dateInterval, 10, 123456);
//  }
//
//  @Test void Game()
//  {
//    assertNotNull(date1);
//    assertNotNull(date2);
//    assertNotNull(dateInterval);
//    assertNotNull(game);
//  }
//
//  @Test void reRollID()
//  {
//    int value = game.getId();
//    game.reRollID();
//    assertNotEquals(value, game.getId());
//  }
//
//  @Test void getId()
//  {
//    int value = game.getId();
//    assertEquals(value, game.getId());
//  }
//
//  @Test void getUserID()
//  {
//    assertEquals(123456, game.getUserID());
//  }
//
//  @Test void getTitle()
//  {
//    assertEquals("Doom", game.getTitle());
//  }
//
//  @Test void getType()
//  {
//    assertEquals("PC", game.getType());
//  }
//
//  @Test void getReleaseYear()
//  {
//    assertEquals(2016, game.getReleaseYear());
//  }
//
//  @Test void deposit()
//  {
//    assertFalse(game.deposit());
//  }
//
//  @Test void getRentalPeriod()
//  {
//    assertEquals(dateInterval, game.getRentalPeriod());
//  }
//
//  @Test void getAvailabilityPeriod()
//  {
//    assertEquals(10, game.getAvailabilityPeriod());
//  }
//
//  @Test void testToString()
//  {
//    int value = game.getId();
//    assertEquals(
//        "Title: Doom, id: " + value + ", type: PC, release year: 2016, "
//            + "deposit: false, rental period: 12/4 - 20/4, availability period: 10",
//        game.toString());
//
//  }
//}