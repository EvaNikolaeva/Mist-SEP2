package mediator;

import model.Game;
import model.GameList;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteGameListModel
{
    GameList getGameList();
    void addGame(Game game);
    void removeGame(int id);
}
