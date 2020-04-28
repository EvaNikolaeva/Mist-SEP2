package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Game;
import viewModel.GameListViewModel;
import viewModel.UserGameListViewModel;

import java.rmi.RemoteException;

public class UserGameListController
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
  private UserGameListViewModel userGameListViewModel;

  public void init(ViewHandler viewHandler, UserGameListViewModel userGameListViewModel,
      Region root) throws RemoteException
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.userGameListViewModel = userGameListViewModel;
    this.list.setItems(userGameListViewModel.getList());
    //need a way to add individual data to each column
//    title.setCellValueFactory(cellData -> cellData.getValue().getCourseProperty());
//    type.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
//    examiner.setCellValueFactory(cellData -> cellData.getValue().getExaminerProperty());
//    externalExaminer.setCellValueFactory(cellData -> cellData.getValue().getExternalExaminerProperty());
//    room.setCellValueFactory(cellData -> cellData.getValue().getRoomProperty());
//    date.setCellValueFactory(cellData -> cellData.getValue().getDateProperty().asString());
//    nr_students.setCellValueFactory(cellData -> cellData.getValue().getNr_studentsProperty());

  }

  public Region getRoot()
  {
    return root;
  }



  public void reset() throws RemoteException {
    this.list.setItems(userGameListViewModel.getList());
  }

  @FXML public void onMyGames() throws RemoteException {
    viewHandler.openView("user");
  }

  @FXML public void onAddGame() throws RemoteException {
    viewHandler.openView("menu");
  }

  @FXML public void onBackList() throws RemoteException {
    viewHandler.openView("list");
  }

    @FXML public void onRemoveGame() throws RemoteException
    {
      if(list.getSelectionModel().getSelectedIndex() < 0)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select a game.", ButtonType.OK);
        alert.showAndWait();
        alert.close();
      }
      else
      {
        Game selectedGame = list.getSelectionModel().getSelectedItem();
        userGameListViewModel.removeGame(selectedGame);
        int index = list.getSelectionModel().getSelectedIndex();

        if (list.getSelectionModel().getSelectedItem() == null)
          this.userGameListViewModel.getList().remove(index);

        this.userGameListViewModel.getList().clear();
        this.list.setItems(userGameListViewModel.getList());
      }
    }
}
