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
import model.User;
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
    this.bio = new SimpleStringProperty("");
    this.username = new SimpleStringProperty("");
  }
public void updateSelectedUser() throws RemoteException {
  this.bio.set(model.getUserDataById(model.getSelectedOtherUserIdBuffer()).getBio());
  this.username.set(model.getUserDataById(model.getSelectedOtherUserIdBuffer()).getUsername());
};
  public ObservableList<Game> getOtherUserGameList() throws RemoteException
  {
    User otherUser = model.getUserDataById(model.getSelectedOtherUserIdBuffer());
    for (int i = 0; i < model.GetGameList().size(); i++)
    {
      if (model.GetGameList().getGame(i).getUserID() == otherUser.getUserID())
      {
        otherUser.getGames().addGame(model.GetGameList().getGame(i));
      }
    }
    list.clear();
    for (int i = 0; i < otherUser.getGames().size(); i++)
    {
      list.add(otherUser.getGames().getGame(i));
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
