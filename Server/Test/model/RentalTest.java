package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest
{
  private User owner;
  private User requester;
  private Game game;
  private Rental rental;

  @BeforeEach void setUp()
  {
    owner = new User("bob", "1234", 12345);
    requester = new User("john", "4321", 54321);
    game = new Game("Doom", "PC", 2016, false, 10, 123456);
    rental = new Rental(owner, requester, game);
  }

  @Test void Rental()
  {
    assertNotNull(owner);
    assertNotNull(requester);
    assertNotNull(game);
    assertNotNull(rental);
  }

  @Test void getOwner()
  {
    assertEquals(owner, rental.getOwner());
  }

  @Test void getRequester()
  {
    assertEquals(requester, rental.getRequester());
  }

  @Test void getGame()
  {
    assertEquals(game, rental.getGame());
  }


  @Test void testToString()
  {
    assertEquals("Doom bob", rental.toString());
  }
}