package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.Game;
import viewModel.MyProfileViewModel;

import java.rmi.RemoteException;

public class MyProfileController
{
  @FXML ListView<Game> incomingTradeList;    //this was "list". Changed it to this value everywhere for the sake of testing
  @FXML ListView<Game> owned;
  @FXML ListView<Game> forTrade;
  @FXML ListView<Game> rented;
  @FXML Label bio;
  private MyProfileViewModel myProfileViewModel;
  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler,
      MyProfileViewModel myProfileViewModel, Region root) throws RemoteException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.incomingTradeList.setItems(myProfileViewModel.getList());
    this.owned.setItems(myProfileViewModel.getList());
    this.rented.setItems(myProfileViewModel.getList());
    this.forTrade.setItems(myProfileViewModel.getList());
    this.bio.textProperty().bind(myProfileViewModel.getBio());
    this.myProfileViewModel = myProfileViewModel;
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset() throws RemoteException
  {
    this.incomingTradeList
        .setItems(myProfileViewModel.getList()); //finish the method getList
  }

  @FXML public void onAddGame() throws RemoteException
  {
    viewHandler.openView("menu");
  }

  @FXML public void onBrowseGames() throws RemoteException
  {
    viewHandler.openView("list");
  }

  @FXML public void onDeleteGame() throws RemoteException
  {
    if (incomingTradeList.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else
    {

      Game selectedGame = incomingTradeList.getSelectionModel().getSelectedItem();
      myProfileViewModel.removeGame(selectedGame);
      int index = incomingTradeList.getSelectionModel().getSelectedIndex();
      if (incomingTradeList.getSelectionModel().getSelectedItem() == null)
      {
        this.myProfileViewModel.getList().remove(index);
      }
      this.myProfileViewModel.getList().clear();
      this.incomingTradeList.setItems(myProfileViewModel.getList());
    }
  }

  @FXML public void onAcceptTrade() throws RemoteException
  {
    Game selectedGame = incomingTradeList.getSelectionModel().getSelectedItem();
    myProfileViewModel.acceptGame(selectedGame, selectedGame.getUserID());
  }

  @FXML public void onDecline() throws RemoteException
  {
    if (incomingTradeList.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else
    {

      Game selectedGame = incomingTradeList.getSelectionModel().getSelectedItem();
      myProfileViewModel.removeGame(selectedGame);
      int index = incomingTradeList.getSelectionModel().getSelectedIndex();
      if (incomingTradeList.getSelectionModel().getSelectedItem() == null)
      {
        this.myProfileViewModel.getList().remove(index);
      }
      this.myProfileViewModel.getList().clear();
      this.incomingTradeList.setItems(myProfileViewModel.getList());
    }
  }

  @FXML public void onEditProfile() throws RemoteException
  {
    viewHandler.openView("editBio");
  }
}
