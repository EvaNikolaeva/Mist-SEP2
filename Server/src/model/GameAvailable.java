package model;

public class GameAvailable implements GameState
{
  @Override public void changeState(Game game)
  {
    game.setState(new GamePending());
  }

  @Override public String status()
  {
    return GameAvailable.class.getSimpleName();
  }
}
