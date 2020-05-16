package model;

import java.util.ArrayList;

public interface MyProfileModel extends Model
{
  ArrayList<Integer> getAllUserOwnedGames();
  ArrayList<Integer> getAllUserPendingGames();
  ArrayList<Integer> getAllUserRentedGames();
  ArrayList<Integer> getAllUserIncomingGames();

  String getUsername(int userID);
  String getBio(int userID);
  void removeGame(int userID, int gameID);
  void acceptIncomingGame(int userID, int gameID);
  void declineIncomingGame(int userID, int gameID);
}
