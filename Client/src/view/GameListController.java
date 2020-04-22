package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import model.Game;
import viewModel.GameListViewModel;

public class GameListController
{
  @FXML TableView<Game> list;
  @FXML TableColumn<String, Game> title;
  @FXML TableColumn<String, Game> type;
  @FXML TableColumn<String, Game> year;
  @FXML TableColumn<String, Game> rentalPeriod;
  @FXML TableColumn<String, Game> availabilityPeriod;
  @FXML TableColumn<String, Game> deposit;
  private Region root;

  private ViewHandler viewHandler;
  private GameListViewModel gameListViewModel;

  public void init(ViewHandler viewHandler, GameListViewModel gameListViewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.gameListViewModel = gameListViewModel;
    this.list.setItems(gameListViewModel.getItems());
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset()
  {

  }

  @FXML public void onAddGame()
  {
    viewHandler.openView("menu");
  }

//  @FXML public void removeGame()
//  {
//    if(list.getSelectionModel().getSelectedIndex() < 0)
//    {
//      Alert alert = new Alert(Alert.AlertType.ERROR, "You have to select a created offer.", ButtonType.OK);
//      alert.showAndWait();
//      alert.close();
//    }
//    else
//    {
//      int index = table.getSelectionModel().getSelectedIndex();
//      this.gameListViewModel.getGames().remove(index);
//      this.model.removeExam(index);
//    }
//  }
}
