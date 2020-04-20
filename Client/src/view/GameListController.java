package view;

import viewModel.GameListViewModel;
import viewModel.GameMenuViewModel;

public class GameListController
{
  @FXMl TableView</*here is an object. String or "Game" object*/> list;
  @FXMl TableColumn title;
  @FXMl TableColumn type;
  @FXMl TableColumn year;
  @FXMl TableColumn rentalPeriod;
  @FXMl TableColumn availabilityPeriod;
  @FXMl TableColumn deposit;
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
//  } there is a need for a 3rd fxml file where you only see your games so you can delete them only there
  // afterwards, in the window with all the games, there will be an update from the 3rd fxml file
}
