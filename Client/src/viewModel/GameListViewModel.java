package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.GameListModel;
import model.Model;

import java.rmi.RemoteException;

public class GameListViewModel
{
  private GameListModel model;
  private ObservableList<Game> availableGames;
  public GameListViewModel(GameListModel model)
  {
    this.model = model;
    this.availableGames = FXCollections.observableArrayList();
  }

  public ObservableList<Game> getAvailableGames() throws RemoteException {
    {
      
    }
   return availableGames;
  }

  public void requestTrade(Game game, int userID) throws RemoteException
  {
    model.requestGame(userID, game.getId());
  }
  public void setSelectedUserId(int id) throws RemoteException {
    model.getOtherUserByID(id);
  }

}
