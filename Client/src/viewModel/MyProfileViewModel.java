package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.Model;
import model.MyProfileModel;
import model.User;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyProfileViewModel
{
  private MyProfileModel model;
  private ObservableList<Game> incomingGames;
  private ObservableList<Game> ownedGames;
  private ObservableList<Game> rentedGames;
  private ObservableList<Game> pendingGames;
  private StringProperty bio;
  private StringProperty username;

  public MyProfileViewModel(MyProfileModel model)
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    this.model = model;
    this.incomingGames = FXCollections.observableArrayList();
    this.ownedGames = FXCollections.observableArrayList();
    this.rentedGames = FXCollections.observableArrayList();
    this.pendingGames = FXCollections.observableArrayList();
    this.bio = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
  }

  public ObservableList<Game> getOwnedGames() throws RemoteException
  {
    return ownedGames;
  }

  public ObservableList<Game> getPendingGames() throws RemoteException
  {
    return pendingGames;
  }

  public ObservableList<Game> getRentedGames() throws RemoteException
  {
    return rentedGames;
  }

  public ObservableList<Game> getIncomingGames() throws RemoteException
  {
    return incomingGames;
  }

  public StringProperty getBio() throws RemoteException
  {
    return bio;
  }

  public StringProperty getUsername() throws RemoteException
  {
    return username;
  }

  public User getLocalUser() throws RemoteException
  {
    return model.getOtherUserByID(model.getLocalUserId());
  }

  public void setBio() throws RemoteException
  {
    model.setBio(getLocalUser().getUserID(),bio.getValue());
  }

  public void removeGame(int gameID) throws RemoteException
  {
    model.removeGame(getLocalUser().getUserID(), gameID);
  }

  public void acceptGame(int gameID) throws RemoteException
  {
    model.acceptIncomingGame(getLocalUser().getUserID(), gameID);
  }
}
