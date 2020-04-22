package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.GameList;
import model.Model;

import java.rmi.RemoteException;

public class UserGameListViewModel
{
  private Model model;
  private ObservableList<Game> list;

  public UserGameListViewModel(Model model)
  {
    this.model = model;
    this.list = FXCollections.observableArrayList();
    //? model.addListener(this);
  }

  public ObservableList<Game> getList() throws RemoteException
  {
    GameList games = model.GetGameList();
    for (int i = 0; i < games.size(); i++)
    {
      list.add(games.getGame(i));
    }
    return list;
  }

  public void removeGame(Game game) throws RemoteException
  {
    GameList games = model.GetGameList();
    for (int i = 0; i < games.size(); i++)
    {
      if (games.getGameById(games.getGame(i).getId()).getId() == game.getId())
        games.removeGame(games.getGame(i).getId());
    }
  }

}
