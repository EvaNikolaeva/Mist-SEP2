package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameListModel
{
  ArrayList<Integer> getAllAvailableGames() throws RemoteException;
  ArrayList<Integer> getAllPendingGames() throws RemoteException;
  void requestGame(int userID, int gameID) throws RemoteException;
  User getOtherUserByID(int userID) throws RemoteException;
}
