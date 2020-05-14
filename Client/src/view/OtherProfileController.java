package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.Game;
import viewModel.MyProfileViewModel;
import viewModel.OtherProfileViewModel;

import java.rmi.RemoteException;

public class OtherProfileController
{
  @FXML ListView<Game> list;
  @FXML Label bio;
  @FXML Label username;
  private OtherProfileViewModel otherProfileViewModel;
  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler,
      OtherProfileViewModel otherProfileViewModel, Region root) throws RemoteException
  {
    otherProfileViewModel.updateSelectedUser();
    this.viewHandler = viewHandler;
    this.root = root;
    this.list.setItems(otherProfileViewModel.getOtherUserGameList());
    this.username.textProperty().bind(otherProfileViewModel.getUsername());
    this.bio.textProperty().bind(otherProfileViewModel.getBio());
    this.otherProfileViewModel = otherProfileViewModel;
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset() throws RemoteException
  {
    otherProfileViewModel.updateSelectedUser();
    this.list
        .setItems(otherProfileViewModel.getOtherUserGameList()); //finish the method getList
  }

  @FXML public void onBrowseGames() throws RemoteException
  {
    viewHandler.openView("list");
  }

  @FXML public void onAddGame() throws RemoteException
  {
    viewHandler.openView("menu");
  }
}
