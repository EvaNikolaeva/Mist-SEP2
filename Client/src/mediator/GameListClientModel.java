package mediator;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface GameListClientModel
{
    void connect() throws RemoteException, NotBoundException, MalformedURLException;
    GameList getGameList();
    void addGame(Game game);
    void removeGame(int id);
    void close();
}
