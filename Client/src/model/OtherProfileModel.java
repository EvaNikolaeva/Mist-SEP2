package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface OtherProfileModel
{
  User getUser(Game game) throws RemoteException;
  Game getGameBuffer();

//  void requestGame(int userID, int gameID) throws RemoteException;
//  String getUsername(int userID) throws RemoteException;
//  String getBio(int userID) throws RemoteException;
//  ArrayList<Integer> getOtherAllUserOwnedGames(int userID) throws RemoteException;
//  ArrayList<Integer> getOtherAllUserPendingGames(int userID) throws RemoteException;
}
