package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Game;
import viewModel.GameListViewModel;

import java.rmi.RemoteException;

public class GameListController
{

  @FXML ListView<Game> list;
  //  @FXML TableColumn<String, Game> title;
  //  @FXML TableColumn<String, Game> type;
  //  @FXML TableColumn<String, Game> year;
  //  @FXML TableColumn<String, Game> rentalPeriod;
  //  @FXML TableColumn<String, Game> availabilityPeriod;
  //  @FXML TableColumn<String, Game> deposit;

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
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset() throws RemoteException
  {
    this.list.setItems(gameListViewModel.getList());
  }

  @FXML public void onMyProfile() throws RemoteException
  {
    viewHandler.openView("profile");
  }

  @FXML public void onAddGame() throws RemoteException
  {
    viewHandler.openView("menu");
  }

  @FXML public void requestTrade() throws RemoteException
  {
    if(list.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
              "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else
    {
      gameListViewModel.requestTrade(list.getSelectionModel().getSelectedItem(),
              list.getSelectionModel().getSelectedItem().getUserID());
    }
  }

  @FXML public void onOtherProfile() throws RemoteException
  {
    if(list.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a game.", ButtonType.OK);
                alert.showAndWait();
                alert.close();
    }
    else
    {
      gameListViewModel.setSelectedUserId(list.getSelectionModel().getSelectedItem().getUserID());
      viewHandler.openView("other");
    }
  }
}
