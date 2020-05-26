package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class OtherProfileViewModel
{
  private StringProperty bio;
  private StringProperty username;
  private ObservableList<Game> ownedGames;
  private OtherProfileModel model;

  public OtherProfileViewModel(OtherProfileModel model) throws RemoteException
  {
    this.model = model;
    this.bio = new SimpleStringProperty();
    this.username = new SimpleStringProperty();
    this.ownedGames = FXCollections.observableArrayList();
  }

  public StringProperty getBio() throws RemoteException, SQLException {
    bio.setValue((model.getUserByUserId(model.getGameBuffer().getUserId())).getBio());
    System.out.println(bio.getValue());
    return bio;
  }

  public StringProperty getUsername() throws RemoteException, SQLException {
    username.setValue(model.getUser(model.getGameBuffer()).getUsername());
    return username;
  }

  public ObservableList<Game> getAllOtherUserOwnedGames() throws RemoteException, SQLException {
    ownedGames.clear();
    User userBuffer = model.getUser(model.getGameBuffer());
    GameList allGames = model.getAllGamesFromServer();
    ownedGames.clear();
    for(int i = 0; i < allGames.size(); i++){
      if(allGames.getGame(i).getUserId() == userBuffer.getUserID()){
        ownedGames.add(allGames.getGame(i));
      }
    }
    return ownedGames;
  }
}
