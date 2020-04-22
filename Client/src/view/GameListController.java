package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import model.Game;
import viewModel.GameListViewModel;

import java.rmi.RemoteException;

public class GameListController
{
  @FXML TableView<Game> list;
  @FXML TableColumn<String, Game> title;
  @FXML TableColumn<String, Game> type;
  @FXML TableColumn<String, Game> year;
  @FXML TableColumn<String, Game> rentalPeriod;
  @FXML TableColumn<String, Game> availabilityPeriod;
  @FXML TableColumn<String, Game> deposit;
  private Region root;

  private ViewHandler viewHandler;
  private GameListViewModel gameListViewModel;

  public void init(ViewHandler viewHandler, GameListViewModel gameListViewModel,
      Region root) throws RemoteException
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.gameListViewModel = gameListViewModel;
    this.list.setItems(gameListViewModel.getList());
    //cel factories here for every table column
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset()
  {

  }

  @FXML public void onMyGames()
  {
    viewHandler.openView("user");
  }
}
