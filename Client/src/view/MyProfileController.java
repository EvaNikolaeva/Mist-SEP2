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
import viewModel.MyProfileViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyProfileController
{
  @FXML ListView<Game> incomingTradeList;    //this was "list". Changed it to this value everywhere for the sake of testing
  @FXML ListView<Game> owned;
  @FXML ListView<Game> pendingTradesList;
  @FXML ListView<Game> rented;
  @FXML Label bio;
  private MyProfileViewModel myProfileViewModel;
  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler,
      MyProfileViewModel myProfileViewModel, Region root)
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    myProfileViewModel.updateUser();
    this.viewHandler = viewHandler;
    this.root = root;
    this.pendingTradesList.setItems(myProfileViewModel.getPendingTradesList());
    this.incomingTradeList.setItems(myProfileViewModel.getIncomingTradeList());
    this.owned.setItems(myProfileViewModel.getOwnedGameList());
    this.rented.setItems(myProfileViewModel.getRentedGameList());
    this.bio.textProperty().bind(myProfileViewModel.getBio());
    this.myProfileViewModel = myProfileViewModel;
    System.out.println(myProfileViewModel.getUser()
        .getUserID()); //this is the main problem. It cannot find the user id. It is always null
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {

    myProfileViewModel.updateUser();
    this.incomingTradeList.setItems(
        myProfileViewModel.getIncomingTradeList()); //finish the method getList
    this.owned.setItems(
        myProfileViewModel.getOwnedGameList()); //finish the method getList
    this.rented.setItems(
        myProfileViewModel.getRentedGameList()); //finish the method getList
    this.pendingTradesList.setItems(myProfileViewModel.getPendingTradesList());
    myProfileViewModel.updateBio();
    System.out.println("bruh");
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

  @FXML public void onDeleteGame() throws RemoteException
  {
    if (owned.getSelectionModel().getSelectedIndex() < 0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "You have to select a game.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
    else
    {
      Game selectedGame = owned.getSelectionModel().getSelectedItem();
      myProfileViewModel.removeGame(selectedGame);
      int index = owned.getSelectionModel().getSelectedIndex();
      if (owned.getSelectionModel().getSelectedItem() == null)
      {
        this.myProfileViewModel.getOwnedGameList()
            .remove(index); //ok java you kinky
      }
      this.myProfileViewModel.getOwnedGameList().clear();
      this.owned.setItems(myProfileViewModel.getOwnedGameList());
    }
  }

  @FXML public void onAcceptTrade() throws RemoteException, InterruptedException, NotBoundException, MalformedURLException {
    Game selectedGame = incomingTradeList.getSelectionModel().getSelectedItem();
    myProfileViewModel.acceptGame(selectedGame);
    Platform.runLater(() -> {
      try {
        reset();
      } catch (RemoteException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (NotBoundException e) {
        e.printStackTrace();
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    });
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

      Game selectedGame = incomingTradeList.getSelectionModel()
          .getSelectedItem();
      myProfileViewModel.removeGame(selectedGame);
      int index = incomingTradeList.getSelectionModel().getSelectedIndex();
      if (incomingTradeList.getSelectionModel().getSelectedItem() == null)
      {
        this.myProfileViewModel.getIncomingTradeList().remove(index);
      }
      this.myProfileViewModel.getIncomingTradeList().clear();
      this.incomingTradeList
          .setItems(myProfileViewModel.getIncomingTradeList());
    }
  }

  @FXML public void onEditProfile()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    viewHandler.openView("editBio");
  }
}
