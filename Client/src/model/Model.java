package model;

import Utility.UnnamedPropertyChangeSubject;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface Model extends UnnamedPropertyChangeSubject
{
    void AddGame(Game game) throws RemoteException;
    void RemoveGame(int id) throws RemoteException;
    GameList GetGameList() throws RemoteException;
    public GameList getUserGamesList() throws RemoteException;
    public void updateUserGames() throws RemoteException;
    public int getUserId();
    public void connect() throws RemoteException, MalformedURLException, InterruptedException, NotBoundException;
    public void onConnected();
}
