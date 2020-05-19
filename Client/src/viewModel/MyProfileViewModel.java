package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyProfileViewModel
{
  private MyProfileModel model;
  private ObservableList<Game> ownedGames;
  private ObservableList<Rental> rentals;
  private StringProperty bio;
  private StringProperty username;

  public MyProfileViewModel(MyProfileModel model)
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    this.model = model;
    this.ownedGames = FXCollections.observableArrayList();
    this.rentals = FXCollections.observableArrayList();
    this.bio = new SimpleStringProperty(model.login(model.getUsername(), model.getPassword()).getBio());
    this.username = new SimpleStringProperty(model.getUsername());
  }

  public ObservableList<Game> getOwnedGames() throws RemoteException
  {
    User userBuffer = model.login(model.getUsername(), model.getPassword());
    ownedGames.clear();
    for(int i = 0; i < userBuffer.getGameList().size(); i++){
      ownedGames.add(userBuffer.getGameList().getGame(i));
    }
    return ownedGames;
  }

  public ObservableList<Rental> getRentals() throws RemoteException
  {
    rentals.clear();
    for(int i = 0; i <  model.getRentalList().size(); i++){
      if(model.getRentalList().getRentals().get(i).getOwner() == model.login(model.getUsername(), model.getPassword())){
        rentals.add(model.getRentalList().getRentals().get(i));
      }
    }
    return rentals;
  }

public StringProperty getBio(){
    return bio;
}
  public StringProperty getUsername(){
    return username;
  }
  public void removeGame(Game game) throws RemoteException
  {
    model.clientRemoveGame(game);
  }

  public void acceptGame(Rental rental) throws RemoteException
  {
    model.clientAcceptIncomingGame(rental);
  }
  public void declineGame(Rental rental) throws RemoteException {
    model.clientDeclineIncomingGame(rental);
  }
}
