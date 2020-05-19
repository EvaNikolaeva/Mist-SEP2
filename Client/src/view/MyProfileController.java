package view;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.Game;
import model.Rental;
import viewModel.MyProfileViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyProfileController
{
  @FXML ListView<Game> ownedGames;
  @FXML ListView<Rental> rentals;
  @FXML Label bio;
  @FXML Label username;
  private MyProfileViewModel myProfileViewModel;
  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler,
      MyProfileViewModel myProfileViewModel, Region root)
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.ownedGames.setItems(myProfileViewModel.getOwnedGames());
    this.rentals.setItems(myProfileViewModel.getRentals());
    this.bio.textProperty().bind(myProfileViewModel.getBio());
    this.myProfileViewModel = myProfileViewModel;
    this.username.textProperty().bind(myProfileViewModel.getUsername());
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    this.ownedGames.setItems(myProfileViewModel.getOwnedGames());
    this.rentals.setItems(myProfileViewModel.getRentals());
  }

  @FXML public void onAddGame()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    viewHandler.openView("menu");
  }

  @FXML public void onBrowseGames()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    viewHandler.openView("list");
  }

  @FXML public void onDelete() throws RemoteException
  {
    if (ownedGames.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else
    {
      Game selectedGame = ownedGames.getSelectionModel().getSelectedItem();
      myProfileViewModel.removeGame(selectedGame);
      int index = ownedGames.getSelectionModel().getSelectedIndex();
      if (ownedGames.getSelectionModel().getSelectedItem() == null)
      {
        this.myProfileViewModel.getOwnedGames()
            .remove(index); //ok java you kinky
      }
      this.myProfileViewModel.getOwnedGames().clear();
      this.ownedGames.setItems(myProfileViewModel.getOwnedGames());
    }
  }

  @FXML public void onAccept()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    Rental selectedRental = rentals.getSelectionModel().getSelectedItem();
    myProfileViewModel.acceptGame(selectedRental);
    Platform.runLater(() -> {
      try
      {
        reset();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

    });
  }

  @FXML public void onDecline() throws RemoteException
  {
    if (rentals.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else
    {
        //CATA: i think this needs to happen in two sides
      Rental selectedRental = rentals.getSelectionModel().getSelectedItem();
      int index = rentals.getSelectionModel().getSelectedItem().getId();
      if(index== 0)
      {
        myProfileViewModel.declineGame(selectedRental);
        this.myProfileViewModel.getRentals().remove(index);
      }
      this.myProfileViewModel.getRentals().clear();
      this.rentals.setItems(myProfileViewModel.getRentals());
    }
  }

  @FXML public void onEdit()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    viewHandler.openView("editBio");
  }
}
