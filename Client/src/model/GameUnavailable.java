package model;

public class GameUnavailable implements GameState
{
  @Override public void changeState(Game game)
  {
    game.setState(new GameAvailable());
  }

  @Override public String status()
  {
    return GameUnavailable.class.getSimpleName();
  }
}
