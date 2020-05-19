package view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.Game;
import viewModel.MyProfileViewModel;
import viewModel.OtherProfileViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class OtherProfileController
{
  @FXML ListView<Game> ownedGames;
  @FXML ListView<Game> pendingGames;
  @FXML Label bio;
  @FXML Label username;
  private OtherProfileViewModel otherProfileViewModel;
  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler,
      OtherProfileViewModel otherProfileViewModel, Region root)
      throws RemoteException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.username.textProperty().bind(otherProfileViewModel.getUsername());
    this.bio.textProperty().bind(otherProfileViewModel.getBio());
    this.otherProfileViewModel = otherProfileViewModel;
    this.ownedGames.setItems(otherProfileViewModel
        .getAllOtherUserOwnedGames());
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset() throws RemoteException
  {
    this.ownedGames.setItems(otherProfileViewModel
            .getAllOtherUserOwnedGames());
  }

  @FXML public void onChat()
  {
    //add Chat Window to ViewHandler and connect
  }

  @FXML public void onBrowseGames()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    viewHandler.openView("list");
  }

  @FXML public void onAddGame()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    viewHandler.openView("menu");
  }

  @FXML public void onMyProfile()
      throws RemoteException, MalformedURLException, InterruptedException,
      NotBoundException
  {
    viewHandler.openView("profile");
  }
}
