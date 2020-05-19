package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Game;
import viewModel.GameListViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class GameListController
{

  @FXML ListView<Game> availableGames;
  @FXML ListView<Game> pendingGames;

  private Region root;
  private ViewHandler viewHandler;
  private GameListViewModel gameListViewModel;

  public void init(ViewHandler viewHandler, GameListViewModel gameListViewModel,
      Region root) throws RemoteException
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.gameListViewModel = gameListViewModel;
    this.availableGames.setItems(gameListViewModel.getAvailableGames());
    this.pendingGames.setItems(gameListViewModel.getPendingGames());
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset() throws RemoteException
  {
    this.availableGames.setItems(gameListViewModel.getAvailableGames());
    this.pendingGames.setItems(gameListViewModel.getPendingGames());
  }

  @FXML public void onMyProfile()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    viewHandler.openView("profile");
  }

  @FXML public void onAddGame()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    viewHandler.openView("menu");
  }

  @FXML public void requestGame() throws RemoteException
  {
    if (availableGames.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else
    {
      gameListViewModel
          .requestTrade(availableGames.getSelectionModel().getSelectedItem(),
              availableGames.getSelectionModel().getSelectedItem().getUserID());
    }
    //    if (pendingGames.getSelectionModel().getSelectedIndex() < 0)     //CATA: dunno if this is necessary, from what i remember you cant send a request to a pending game
    //    {
    //      Alert alert = new Alert(Alert.AlertType.ERROR,
    //          "You have to select a game.", ButtonType.OK);
    //      alert.showAndWait();
    //      alert.close();
    //    }
    //    else
    //    {
    //      gameListViewModel
    //          .requestTrade(pendingGames.getSelectionModel().getSelectedItem(),
    //              pendingGames.getSelectionModel().getSelectedItem().getUserID());
    //    }
  }

  @FXML public void onOtherProfile()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    if (availableGames.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else
    {
      gameListViewModel.setSelectedUserId(
          availableGames.getSelectionModel().getSelectedItem().getUserID());
      viewHandler.openView("other");
    }
    if (pendingGames.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else
    {
      gameListViewModel.setSelectedUserId(
          pendingGames.getSelectionModel().getSelectedItem().getUserID());
      viewHandler.openView("other");
    }
  }
}
