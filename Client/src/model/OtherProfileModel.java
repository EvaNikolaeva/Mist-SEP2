package model;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OtherProfileModel
{
  User getUser(Game game) throws RemoteException, SQLException;
  Game getGameBuffer();
  GameList getAllGamesFromServer() throws RemoteException, SQLException;
//  void requestGame(int userID, int gameID) throws RemoteException;
//  String getUsername(int userID) throws RemoteException;
//  String getBio(int userID) throws RemoteException;
//  ArrayList<Integer> getOtherAllUserOwnedGames(int userID) throws RemoteException;
//  ArrayList<Integer> getOtherAllUserPendingGames(int userID) throws RemoteException;
}
