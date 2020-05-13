package model;

import utility.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  void AddGame(Game game);
  void RemoveGame(int id);
  GameList GetGameList();
  User getUserData(String username);
}
