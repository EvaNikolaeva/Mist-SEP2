package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.GameList;
import model.Model;

public class GameListViewModel
{
  private Model model;
  private ObservableList<Game> list;

  public GameListViewModel(Model model)
  {
    this.model = model;
    this.list = FXCollections.observableArrayList();
  }

  public ObservableList<Game> getList()
  {
    return list;
  }

}
