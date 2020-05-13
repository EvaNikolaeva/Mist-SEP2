package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.Game;
import viewModel.MyProfileViewModel;

import java.rmi.RemoteException;

public class MyProfileController
{
  @FXML
  ListView<Game> list;
  private MyProfileViewModel myProfileViewModel;
  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler,
                   MyProfileViewModel myProfileViewModel, Region root) throws RemoteException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.list.setItems(myProfileViewModel.getList());
    this.myProfileViewModel = myProfileViewModel;
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset() throws RemoteException
  {
    this.list
            .setItems(myProfileViewModel.getList()); //finish the method getList
  }

  @FXML
  public void onMyProfile() throws RemoteException
  {
    viewHandler.openView("profile");
  }

  @FXML
  public void onAddGame() throws RemoteException
  {
    viewHandler.openView("menu");
  }

  @FXML
  public void onBrowseGames() throws RemoteException
  {
    viewHandler.openView("list");
  }

  @FXML
  public void onDeleteGame() throws RemoteException
  {
    if (list.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
              "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    } else
    {

      Game selectedGame = list.getSelectionModel().getSelectedItem();
      myProfileViewModel.removeGame(selectedGame);
      int index = list.getSelectionModel().getSelectedIndex();
      if (list.getSelectionModel().getSelectedItem() == null)
      {
        this.myProfileViewModel.getList().remove(index);
      }
      this.myProfileViewModel.getList().clear();
      this.list.setItems(myProfileViewModel.getList());
    }
  }

  @FXML
  public void onAcceptTrade() throws RemoteException
  {
    Game selectedGame = list.getSelectionModel().getSelectedItem();
    myProfileViewModel.acceptGame(selectedGame, selectedGame.getUserID());
  }


  @FXML
  public void onDecline() throws RemoteException
  {
    if (list.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
              "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    } else
    {

      Game selectedGame = list.getSelectionModel().getSelectedItem();
      myProfileViewModel.removeGame(selectedGame);
      int index = list.getSelectionModel().getSelectedIndex();
      if (list.getSelectionModel().getSelectedItem() == null)
      {
        this.myProfileViewModel.getList().remove(index);
      }
      this.myProfileViewModel.getList().clear();
      this.list.setItems(myProfileViewModel.getList());
    }
  }
    @FXML public void onEditProfile () throws RemoteException
    {
      viewHandler.openView("editBio");
    }
  }
