package model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameListModel {
    GameList getAllGamesFromServer() throws RemoteException;

    void clientRequestGame(User requester, Game game) throws RemoteException;

    void setGameBuffer(Game game);

    Game getGameBuffer();

    String getUsername();

    String getPassword();

    User login(String username, String password) throws RemoteException;

//  ArrayList<Integer> getAllAvailableGames() throws RemoteException;
//  ArrayList<Integer> getAllPendingGames() throws RemoteException;
//  void requestGame(int userID, int gameID) throws RemoteException;
//  User getOtherUserByID(int userID) throws RemoteException;
}
