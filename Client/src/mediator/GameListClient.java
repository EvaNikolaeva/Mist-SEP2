package mediator;

import model.Game;
import model.GameList;
import model.Model;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameListClient implements GameListClientModel
{
    private Model model;
    private RemoteGameListModel remoteGameListModel;

    public GameListClient(Model model)
    {
        this.model = model;
    }

    @Override
    public void connect() throws RemoteException, NotBoundException, MalformedURLException
    {
        remoteGameListModel = (RemoteGameListModel) Naming.lookup("rmi://localhost:1099/games");
        UnicastRemoteObject.exportObject((Remote) this, 0);
    }

    @Override
    public GameList getGameList()
    {
        return remoteGameListModel.getGameList();
    }

    @Override
    public void addGame(Game game)
    {
        remoteGameListModel.addGame(game);
    }

    @Override
    public void removeGame(int id)
    {
        remoteGameListModel.removeGame(id);
    }

    @Override
    public void close()
    {
    }

}
