package viewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MyProfileViewModel implements PropertyChangeListener
{
  private MyProfileModel model;
  private ObservableList<Game> ownedGames;
  private ObservableList<Game> rentedGames;
  private ObservableList<Rental> rentals;
  private ObservableList<Rental> pendingRentals;
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
    this.pendingRentals = FXCollections.observableArrayList();
    this.bio = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    //model.addListener(this);
  }

  public ObservableList<Game> getOwnedGames()
      throws RemoteException, SQLException
  {
    User userBuffer = model.login(model.getUsername(), model.getPassword());
    GameList allGames = model.getAllGamesFromServer();
    ownedGames.clear();
    for (int i = 0; i < allGames.size(); i++)
    {
      if (allGames.getGame(i).getUserId() == userBuffer.getUserID())
      {
        ownedGames.add(allGames.getGame(i));
      }
    }
    return ownedGames;
  }

  public User getUser() throws RemoteException, SQLException
  {
   return model.getUser();
  }

  public ObservableList<Game> getRentedGames()
      throws RemoteException, SQLException
  {
    User userBuffer = model.login(model.getUsername(), model.getPassword());
    GameList rentedGamesList = model.getAllRentedGames(model.login(model.getUsername(), model.getPassword()));
    rentedGames.clear();
    for (int i = 0; i < rentedGamesList.size(); i++)
    {
      if(!(rentedGamesList.getGame(i).getId() == -1)){
          rentedGames.add(rentedGamesList.getGame(i));
      }
    }
    return rentedGames;
  }

  public ObservableList<Rental> getRentals()
      throws RemoteException, SQLException
  {

    RentalList rentalList = model.getRentalList();
    rentals.clear();
    for (int i = 0; i < rentalList.size(); i++)
    {
      if(!(rentalList.getRentals().get(i).getId() == -1)){
        if (rentalList.getRentals().get(i).getOwner().getUserID() == model
                .login(model.getUsername(), model.getPassword()).getUserID())
        {
          rentals.add(rentalList.getRentals().get(i));
        }
      }
    }
    return rentals;

  }

  public ObservableList<Rental> getPendingRentals()
      throws RemoteException, SQLException
  {
    pendingRentals.clear();
    for (int i = 0; i < model.getRentalList().getRentals().size(); i++)
    {
      if(!(model.getRentalList().getRentals().get(i).getId() == -1)){
        if (model.getRentalList().getRentals().get(i).getRequester().getUserID()
                == model.login(model.getUsername(), model.getPassword()).getUserID())
        {
          pendingRentals.add(model.getRentalList().getRentals().get(i));
        }
      }
    }
    return pendingRentals;
  }

  public StringProperty getBio() throws RemoteException, SQLException
  {
    bio.setValue(
        model.login(model.getUsername(), model.getPassword()).getBio());
    System.out.println(bio.getValue() + " Users bio");
    return bio;
  }

  public StringProperty getUsername()
  {
    username.setValue(model.getUsername());
    return username;
  }

  public void removeUser(User user) throws RemoteException, SQLException
  {
    model.clientRemoveUser(user);
  }

  public void removeGame(Game game) throws RemoteException, SQLException
  {
    model.clientRemoveGame(game);
  }

  public void acceptGame(Rental rental) throws RemoteException, SQLException
  {
    model.clientAcceptIncomingGame(rental);
  }

  public void declineGame(Rental rental) throws RemoteException, SQLException
  {
    model.clientDeclineIncomingGame(rental);
  }

  public void setGameAvailable(Game game) throws RemoteException, SQLException
  {
    model.setGameAvailabilityTrue(game);
  }

  public void reload() throws RemoteException, SQLException{
    getRentals();
    getPendingRentals();
    getRentedGames();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Platform.runLater(() -> {
      if(evt.getPropertyName().equals("gameRentalUpdate")){
        System.out.println("Attempt was made");
      try {
        reload();
      } catch (RemoteException e) {
        e.printStackTrace();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    });
  }
}
