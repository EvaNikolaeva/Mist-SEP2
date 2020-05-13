package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.Game;
import model.GameList;
import model.Model;
import view.ViewHandler;

import java.rmi.RemoteException;

public class OtherProfileViewModel
{
  private StringProperty bio;
  private StringProperty username;
  private ObservableList<Game> list;

  private Model model;

  public OtherProfileViewModel(Model model) throws RemoteException
  {
    this.model = model;
    this.list = FXCollections.observableArrayList();
    this.bio = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
  }

  public ObservableList<Game> getList() throws RemoteException
  {
    GameList games = model.GetGameList();
    list.clear();
    for (int i = 0; i < games.size(); i++)
    {
      list.add(games.getGame(i));
    }
    return list;
  }

  public StringProperty getBio()
  {
    return bio;
  }

  public StringProperty getUsername()
  {
    return username;
  }
}
