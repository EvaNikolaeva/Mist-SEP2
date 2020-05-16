package model;

public class GamePending implements GameState
{
  @Override public void changeState(Game game)
  {
    game.setState(new GameUnavailable());
  }

  @Override public String status()
  {
    return GamePending.class.getSimpleName();
  }
}
