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

public class GameListClient implements GameListClientModel, Remote
{
  private Model model;
  private RemoteGameListModel remoteGameListModel;

  public GameListClient(Model model) throws RemoteException, NotBoundException, MalformedURLException {
    this.model = model;
    connect();
  }

  @Override public void connect()
      throws RemoteException, NotBoundException, MalformedURLException

  {
    remoteGameListModel = (RemoteGameListModel) Naming.lookup("rmi://localhost:1099/games");
    UnicastRemoteObject.exportObject(this, 0);
    model.updateUserGames();
  }

  @Override public GameList getGameList() throws RemoteException {
    return remoteGameListModel.getGameList();
  }

  @Override public void addGame(Game game) throws RemoteException {
    remoteGameListModel.addGame(game);
  }

  @Override public void removeGame(int id) throws RemoteException {
    remoteGameListModel.removeGame(id);
  }

  @Override public void close()
  {
  }

}
