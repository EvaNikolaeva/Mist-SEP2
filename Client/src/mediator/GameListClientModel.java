package mediator;

import model.Game;
import model.GameList;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameListClientModel
{
    void connect()
        throws RemoteException, NotBoundException, MalformedURLException;
    GameList getGameList();
    void addGame(Game game);
    void removeGame(int id);
    void close();
}
