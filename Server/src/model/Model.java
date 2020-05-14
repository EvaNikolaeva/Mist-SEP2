package model;

import utility.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  void AddGame(Game game);
  void RemoveGame(int id);
  GameList GetGameList();
  User getUserData(String username);
  User getUserDataById(int id);
  void acceptTrade(Game game,int  userID);
  void declineTrade(Game game, int userID);
  void addToPending(Game game, int userID);
  void addUser(User user);
  void setUserBio(User user, String bioText);
  void requestTrade(Game game, int targetID, int requesterID);

  void addToIncoming(Game game, int userID);
  void removeFromIncoming(Game game, int userID);
}
