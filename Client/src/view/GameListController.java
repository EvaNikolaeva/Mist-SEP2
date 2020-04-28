package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.Game;
import viewModel.GameListViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
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
  private double x, y;

  public void init(ViewHandler viewHandler, GameListViewModel gameListViewModel,
      Region root) throws RemoteException
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.gameListViewModel = gameListViewModel;
    this.list.setItems(gameListViewModel.getList());
    this.x = 0;
    this.y = 0;
  }
  public Region getRoot()
  {
    return root;
  }

  public void reset() throws RemoteException {
    this.list.setItems(gameListViewModel.getList());
  }

  @FXML public void onMyGames() throws RemoteException, InterruptedException, NotBoundException, MalformedURLException {
    viewHandler.openView("user");
  }
}
