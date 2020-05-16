package model;

public interface GameState
{
  void changeState(Game game);
  String status();
}
