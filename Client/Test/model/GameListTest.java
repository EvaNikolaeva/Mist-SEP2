package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameListTest
{
  private GameList games;
  private Game game1;
  private Game game2;
  private Game game3;

  @BeforeEach void setUp()
  {
    games = new GameList();
    game1 = new Game("Doom", "PC", 2016, false, 10, 123456);
    game2 = new Game("Warcraft 3", "PC", 2004, true, 30, 987654);
    game3 = new Game("Dark Souls", "PS3", 2009, false, 15, 555555);
  }

  @Test void GameList()
  {
    assertNotNull(games);
    assertNotNull(game1);
    assertNotNull(game2);
    assertNotNull(game3);
  }

  @Test void addGameNull()
  {
    games.addGame(null);
    assertEquals(0, games.size());
  }

  @Test void addGameOne()
  {
    games.addGame(game1);
    assertEquals(1, games.size());
  }

  @Test void addGameTwice()
  {
    games.addGame(game1);
    games.addGame(game1);
    assertEquals(1, games.size());
  }

  @Test void addGameMany()
  {
    games.addGame(game1);
    games.addGame(game2);
    games.addGame(game3);
    assertEquals(3, games.size());
  }

  @Test void removeGameEmptyList()
  {
    int value = game1.getId();
    games.removeGame(value);
    assertEquals(0, games.size());
  }

  @Test void removeGameOne()
  {
    int value = game1.getId();
    games.addGame(game1);
    games.removeGame(value);
    assertEquals(0, games.size());
  }

  @Test void removeGameMany()
  {
    int value = game1.getId();
    int value1 = game2.getId();
    games.addGame(game1);
    games.addGame(game2);
    games.removeGame(value);
    games.removeGame(value1);
    assertEquals(0, games.size());
  }

  @Test void removeGameTwice()
  {
    int value = game1.getId();
    games.addGame(game1);
    games.removeGame(value);
    games.removeGame(value);
    assertEquals(0, games.size());
  }

  @Test void removeGameFromMultipleOnes()
  {
    int value = game1.getId();
    games.addGame(game1);
    games.addGame(game2);
    games.removeGame(value);
    assertEquals(1, games.size());
  }

  @Test void getGameById()
  {
    int value = game1.getId();
    games.addGame(game1);
    assertEquals(game1, games.getGameById(value));
  }

  @Test void getGameByInvalidId()
  {
    int value = 0;
    games.addGame(game1);
    assertNull(games.getGameById(value));
  }

  @Test void getGameByWrongId()
  {
    games.addGame(game1);
    assertNull(games.getGameById(123));
  }

  @Test void getGameMinus()
  {
    games.addGame(game1);
    assertNull(games.getGame(-5));
  }

  @Test void getGameNull()
  {
    games.addGame(game1);
    assertNull(games.getGame(4));
  }

  @Test void size()
  {
    assertEquals(0, games.size());
  }

  @Test void sizeMinus()
  {
    assertNotEquals(-4, games.size());
  }

}