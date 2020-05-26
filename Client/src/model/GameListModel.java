package model;

import Utility.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GameListModel extends UnnamedPropertyChangeSubject{
    GameList getAllGamesFromServer() throws RemoteException, SQLException;

    void clientRequestGame(User requester, Game game) throws RemoteException, SQLException;

    void setGameBuffer(Game game);

    Game getGameBuffer();

    String getUsername();

    String getPassword();

    User login(String username, String password) throws RemoteException, SQLException;

    void gameAddedOnServer(Game game) throws RemoteException, SQLException;

//  ArrayList<Integer> getAllAvailableGames() throws RemoteException;
//  ArrayList<Integer> getAllPendingGames() throws RemoteException;
//  void requestGame(int userID, int gameID) throws RemoteException;
//  User getOtherUserByID(int userID) throws RemoteException;
}
