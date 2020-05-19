package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.Model;
import model.User;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class OtherProfileViewModel
{
  private StringProperty bio;
  private StringProperty username;
  private ObservableList<Game> ownedGames;
  private Model model;

  public OtherProfileViewModel(Model model) throws RemoteException
  {
    this.model = model;
    this.bio = new SimpleStringProperty(model.getUser(model.getGameBuffer()).getBio());
    this.username = new SimpleStringProperty(model.getUser(model.getGameBuffer()).getUsername());
    this.ownedGames = FXCollections.observableArrayList();
  }

  public StringProperty getBio()
  {
    return bio;
  }

  public StringProperty getUsername()
  {
    return username;
  }
  public ObservableList<Game> getAllOtherUserOwnedGames(int userID)
      throws RemoteException
  {
ownedGames.clear();
    for (int i = 0; i < model.getUser(model.getGameBuffer()).getGameList().size(); i++)
    {
      ownedGames.add(model.getUser(model.getGameBuffer()).getGameList().getGame(i));
    }
    return ownedGames;
  }
}
