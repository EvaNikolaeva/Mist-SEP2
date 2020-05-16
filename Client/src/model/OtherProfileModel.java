package model;

import java.util.ArrayList;

public interface OtherProfileModel extends Model
{
  void requestGame(int userID, int gameID);
  String getUsername(int userID);
  String getBio(int userID);
  ArrayList<Integer> getOtherAllUserOwnedGames(int userID);
  ArrayList<Integer> getOtherAllUserPendingGames(int userID);
}
