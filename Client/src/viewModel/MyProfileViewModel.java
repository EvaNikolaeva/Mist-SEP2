package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.Model;
import model.User;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyProfileViewModel
{
  private Model model;
  private ObservableList<Game> incomingTradeList;
  private ObservableList<Game> owned;
  private ObservableList<Game> rented;
  private ObservableList<Game> pending;
  private StringProperty bio;

  public MyProfileViewModel(Model model)
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    this.model = model;
    this.incomingTradeList = FXCollections.observableArrayList();
    this.owned = FXCollections.observableArrayList();
    this.rented = FXCollections.observableArrayList();
    this.pending = FXCollections.observableArrayList();
    this.bio = new SimpleStringProperty();
  }

  public User getUser() throws RemoteException
  {
    return model.getLocalUser();
  }

  public void updateBio() throws RemoteException
  {
    bio.setValue(model.getLocalUser().getBio());
  }

  public StringProperty getBio() throws RemoteException
  {
    bio.setValue(model.getLocalUser().getBio());
    return bio;
  }

  public ObservableList<Game> getIncomingTradeList() throws RemoteException
  {
    GameList games = getUser().getIncomingGameRequests();
    incomingTradeList.clear();
    for (int i = 0; i < games.size(); i++)
    {
      incomingTradeList.add(games.getGame(i));
    }
    return incomingTradeList;
  }

  public ObservableList<Game> getOwnedGameList() throws RemoteException
  {
    GameList games = getUser().getGames();
    owned.clear();
    for (int i = 0; i < games.size(); i++)
    {
      owned.add(games.getGame(i));
    }
    return owned;
  }

  public ObservableList<Game> getRentedGameList() throws RemoteException
  {
    GameList games = getUser().getRentedGames();
    rented.clear();
    for (int i = 0; i < games.size(); i++)
    {
      rented.add(games.getGame(i));
    }
    return rented;
  }

  public void updateUser()
      throws RemoteException, MalformedURLException, InterruptedException,
      NotBoundException
  {
    model.updateUser();
  }

  public ObservableList<Game> getPendingTradesList() throws RemoteException
  {
    GameList games = getUser().getPendingGames();
    pending.clear();
    for (int i = 0; i < games.size(); i++)
    {
      pending.add(games.getGame(i));
    }
    return pending;
  }

  public void removeGame(Game game) throws RemoteException
  {
    model.RemoveGame(game.getId());
  }

  public void acceptGame(Game game) throws RemoteException
  {
    model.acceptTrade(game);
  }

  //    @Override
  //    public void propertyChange(PropertyChangeEvent evt)
  //    {
  //        System.out.println("EVT VALUE PRINT OUT -----------------" + evt.getNewValue());
  //        Platform.runLater(() -> incomingTradeList.add((Game) evt.getNewValue()));
  //    }

}
