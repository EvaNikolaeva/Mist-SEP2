package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.GameList;
import model.Model;

import java.rmi.RemoteException;

public class GameListViewModel
{
  private Model model;
  private ObservableList<Game> list;
  public GameListViewModel(Model model)
  {
    this.model = model;
    this.list = FXCollections.observableArrayList();
  }

  public ObservableList<Game> getList() throws RemoteException {
    GameList games = model.GetGameList();
    list.clear();
    for (int i = 0; i < games.size(); i++)
    {
      list.add(games.getGame(i));
      System.out.println("added game to list" + games.getGame(i));
    }
    return list;
  }

  public void requestTrade(Game game, int userID) throws RemoteException
  {
    model.requestTrade(game, userID);
  }
  public void setSelectedUserId(int id){
    model.setSelectedOtherUserIdBuffer(id);
  }

}
