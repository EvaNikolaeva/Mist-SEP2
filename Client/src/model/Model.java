package model;

import Utility.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;

public interface Model extends UnnamedPropertyChangeSubject
{
    void AddGame(Game game) throws RemoteException;
    void RemoveGame(int id) throws RemoteException;
    GameList GetGameList() throws RemoteException;
    public GameList getUserGamesList() throws RemoteException;
    public void updateUserGames() throws RemoteException;
}
