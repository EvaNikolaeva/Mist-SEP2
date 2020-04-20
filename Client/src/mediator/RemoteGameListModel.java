package mediator;

import model.Game;
import model.GameList;

import java.rmi.Remote;

public interface RemoteGameListModel extends Remote
{
    GameList getGameList();
    void addGame(Game game);
    void removeGame(int id);
}
