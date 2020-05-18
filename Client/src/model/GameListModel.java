package model;

import java.util.ArrayList;

public interface GameListModel
{
  ArrayList<Integer> getAllAvailableGames();
  ArrayList<Integer> getAllPendingGames();
  void requestGame(int userID, int gameID);
}
