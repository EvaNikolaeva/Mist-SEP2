package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MyProfileViewModel
{
  private MyProfileModel model;
  private ObservableList<Game> ownedGames;
  private ObservableList<Game> rentedGames;
  private ObservableList<Rental> rentals;
  private StringProperty bio;
  private StringProperty username;

  public MyProfileViewModel(MyProfileModel model)
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    this.model = model;
    this.ownedGames = FXCollections.observableArrayList();
    this.rentedGames = FXCollections.observableArrayList();
    this.rentals = FXCollections.observableArrayList();
    this.bio = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
  }

  public ObservableList<Game> getOwnedGames() throws RemoteException, SQLException {
    User userBuffer = model.login(model.getUsername(), model.getPassword());
    GameList allGames = model.getAllGamesFromServer();
    ownedGames.clear();
    for(int i = 0; i < allGames.size(); i++){
      if(allGames.getGame(i).getUserId() == userBuffer.getUserID()){
        ownedGames.add(allGames.getGame(i));
      }
    }
    return ownedGames;
  }
  public ObservableList<Game> getRentedGames() throws RemoteException, SQLException {
    User userBuffer = model.login(model.getUsername(), model.getPassword());
    rentedGames.clear();
    for(int i = 0; i < userBuffer.getRentedGameList().size(); i++){
      rentedGames.add(userBuffer.getRentedGameList().getGame(i));
    }
    return rentedGames;
  }

  public ObservableList<Rental> getRentals() throws RemoteException, SQLException {
    rentals.clear();
    for(int i = 0; i <  model.getRentalList().getRentals().size(); i++){
      if(model.getRentalList().getRentals().get(i).getOwner().getUserID() == model.login(model.getUsername(), model.getPassword()).getUserID()){
        rentals.add(model.getRentalList().getRentals().get(i));
      }
    }
    return rentals;
  }

public StringProperty getBio() throws RemoteException, SQLException {
    bio.setValue(model.login(model.getUsername(), model.getPassword()).getBio());
  System.out.println(bio.getValue() + " Users bio");
    return bio;
}
  public StringProperty getUsername(){
    username.setValue(model.getUsername());
    return username;
  }
  public void removeGame(Game game) throws RemoteException, SQLException {
    model.clientRemoveGame(game);
  }

  public void acceptGame(Rental rental) throws RemoteException, SQLException {
    model.clientAcceptIncomingGame(rental);
  }
  public void declineGame(Rental rental) throws RemoteException, SQLException {
    model.clientDeclineIncomingGame(rental);
  }
  public void setGameAvailable(Game game) throws RemoteException, SQLException {
model.setGameAvailabilityTrue(game);
  }
}
