package model;

import java.rmi.RemoteException;

public interface Model
{
    void AddGame(Game game) throws RemoteException;
    void RemoveGame(int id) throws RemoteException;
    GameList GetGameList() throws RemoteException;
    public GameList getUserGamesList();
}
