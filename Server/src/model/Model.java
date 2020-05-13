package model;

import utility.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  void AddGame(Game game);
  void RemoveGame(int id);
  GameList GetGameList();
  User getUserData(String username);
  void acceptTrade(Game game,int  userID);
  void declineTrade(Game game, int userID);
  void addToPending(Game game, int userID);
}
