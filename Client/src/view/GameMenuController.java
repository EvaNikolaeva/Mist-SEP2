package view;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewModel.GameMenuViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class GameMenuController
{
  @FXML TextField availabilityPeriod;
  @FXML TextField name;
  @FXML TextField type;
  @FXML TextField releaseYear;
  @FXML CheckBox deposit;

  private Region root;
  private ViewHandler viewHandler;
  private GameMenuViewModel gameMenuViewModel;
  private StringProperty responseMessage;


  public void init(ViewHandler viewHandler, GameMenuViewModel gameMenuViewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.gameMenuViewModel = gameMenuViewModel;
    this.name.textProperty().bindBidirectional(gameMenuViewModel.getName());
    this.type.textProperty().bindBidirectional(gameMenuViewModel.getType());
    this.releaseYear.textProperty()
        .bindBidirectional(gameMenuViewModel.getReleaseYear());
    this.availabilityPeriod.textProperty()
        .bindBidirectional(gameMenuViewModel.getAvailabilityPeriod());
    this.deposit.allowIndeterminateProperty()
        .bindBidirectional(gameMenuViewModel.getCheckBox());
    this.responseMessage = gameMenuViewModel.getResponseMessage();
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset()
  {
    name.clear();
    type.clear();
    releaseYear.clear();
    deposit.setSelected(false);
    availabilityPeriod.clear();
  }

  @FXML public void onReset()
  {
    reset();
  }

  @FXML public void onSubmit() throws RemoteException
  {
    gameMenuViewModel.addCurrentGame();
    Platform.runLater(() -> {
      try
      {
        checkGame();
      }
      catch (RemoteException | InterruptedException | NotBoundException | MalformedURLException e)
      {
        e.printStackTrace();
      }
    });

  }

  public void checkGame()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    System.out.println(responseMessage);
    if (responseMessage.getValue().equals("Success"))
    {
      viewHandler.openView("list");
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.ERROR, responseMessage.getValue(),
          ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
  }

  @FXML public void onBack()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    viewHandler.openView("list");
  }
}
