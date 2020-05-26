package model;

import DBSConnection.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManager implements Model
{

  private GameDAO gameList;
  private RentalDAO rentalList;
  private UserDAO userList;
  private RentedGameDAO rentedList;

  public ModelManager() throws SQLException {
    this.gameList = GameDAOImpl.getInstance();
    this.userList = UserDAOImpl.getInstance();
    this.rentalList = RentalDAOImpl.getInstance();
    this.rentedList = RentedGameDAOImpl.getInstance();

  }

  @Override public User getUserByID(int gameID) throws SQLException {
if(userList.getUserByUserID(gameID) != null){
  return userList.getUserByUserID(gameID);
}
else{
  return null;
}
  }

  @Override public User getUserByCredentials(String username, String password) throws SQLException {
    return userList.getUserByCredentials(username, password);
  }

  @Override public void setUserBio(User user, String bio) throws RemoteException, SQLException {
   userList.setBio(user, bio);
  }

  @Override public void requestGame(User requester, Game game)
          throws RemoteException, SQLException {
    rentalList.addRental(userList.getUserByUserID(game.getUserId()), requester, game);
    //add to rental in dbs to set the game to unavailable
  }

  @Override public void acceptGame(Rental rental) throws RemoteException, SQLException {
 rentalList.acceptRental(rental);
  }

  @Override public void declineGame(Rental rental) throws RemoteException, SQLException {
    rentalList.declineRental(rental);
  }

  @Override public GameList getFullListOfGames() throws RemoteException, SQLException {
    GameList gameListObject = new GameList();
    ArrayList<Game> gamesDBS = gameList.getAvailableGames();
    ArrayList<Game> unavailableGames = gameList.getUnavailableGames();
    for(int i = 0; i < gamesDBS.size(); i++){
      gameListObject.addGame(gamesDBS.get(i));
    }
    for(int i = 0; i< unavailableGames.size(); i++){
        gameListObject.addGame(unavailableGames.get(i));
    }
return gameListObject;
  }

  @Override public void addGame(Game game) throws RemoteException, SQLException {
gameList.addGame(game.getTitle(), game.getType(), game.getReleaseYear(), game.deposit(), game.getAvailabilityPeriod(), game.getUserId());
  }

  @Override public void removeGame(Game game) throws RemoteException, SQLException {
 gameList.removeGame(game);
 //Change the viewModel on the client
  }

  @Override public Game getGameByIndex(int index)
  {
    return null;
    //missing
  }

  @Override public Game getGameByID(int gameID) throws SQLException {
    return gameList.getGameById(gameID);
  }

  @Override public User getUserByGame(Game game) throws SQLException {
    return userList.getUserByUserID(game.getUserId());
  }

  @Override public int getSizeOfGameList() throws SQLException {
    return gameList.size();
  }

  @Override public RentalList getRentalList() throws SQLException {
    RentalList rentalListObject = new RentalList();
    ArrayList<Rental> rentalDBS = rentalList.getRentals();
    for(int i = 0; i < rentalDBS.size(); i++){
      rentalListObject.addRental(rentalDBS.get(i));
    }
    return rentalListObject;
  }

  @Override public void registerUser(String username, String password) throws SQLException {
    userList.registerUser(username, password);
  }

  @Override public void setGameAvailableTrue(Game game) throws SQLException {
gameList.setAvailable(game);
  }
  @Override public GameList getRentedGames(User user) throws SQLException{
      GameList rentedGamesObj = new GameList();
      ArrayList<Game> rentedArray = rentedList.getRentedGames(user);
      for(int i = 0; i < rentedArray.size(); i++){
          rentedGamesObj.addGame(rentedArray.get(i));
      }
      return rentedGamesObj;
  }
}

