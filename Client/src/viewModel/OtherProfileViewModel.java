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
  private ObservableList<Game> pendingGames;
  private Model model;

  public OtherProfileViewModel(Model model) throws RemoteException
  {
    this.model = model;
    this.bio = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    this.ownedGames = FXCollections.observableArrayList();
    this.pendingGames = FXCollections.observableArrayList();
  }

  public int getUserID()
  {
    return model.getLocalUserId();
  }

  public StringProperty getBio()
  {
    return bio;
  }

  public StringProperty getUsername()
  {
    return username;
  }

  public String getUsername(int userID) throws RemoteException
  {
    return model.getUsername(userID);
  }

  public String getBio(int userID) throws RemoteException
  {
    return model.getBio(userID);
  }

  public ObservableList<Game> getAllOtherUserOwnedGames(int userID)
      throws RemoteException
  {
    ArrayList<Integer> games = model.getOtherAllUserOwnedGames(userID);
    for (int i = 0; i < model.getOtherAllUserOwnedGames(userID).size(); i++)
    {
      games.add(model.getOtherUserByID(userID).getOwnedGames().get(i));
    }
    return ownedGames;
  }

  public ObservableList<Game> getAllOtherUserPendingGames(int userID)
      throws RemoteException
  {
    ArrayList<Integer> games = model.getOtherAllUserOwnedGames(userID);
    for (int i = 0; i < model.getOtherAllUserPendingGames(userID).size(); i++)
    {
      games.add(model.getOtherUserByID(userID).getPendingGames().get(i));
    }
    return ownedGames;
  }
}
