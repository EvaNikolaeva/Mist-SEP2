package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class ModelManager implements Model
{
  private PropertyChangeSupport propertyChangeSupport;
  private GameList gameList;
  private UserList userList;
  private RentalList rentalList;

  public ModelManager()
  {
    this.gameList = new GameList();
    this.userList = new UserList();
    this.rentalList = new RentalList();
    this.propertyChangeSupport = new PropertyChangeSupport(this);
  }

  @Override public User getUserByID(int gameID)
  {
    for (int i = 0; i < userList.size(); i++)
    {
      if (userList.getUserByIndex(i).ownsGame(gameList.getGameById(gameID)))
      {
        return userList.getUserByIndex(i);
      }
    }
    return null;
  }

  @Override public User getUserByCredentials(String username, String password)
  {
    return userList.getUserByCredentials(username, password);
  }

  @Override public void setUserBio(User user, String bio) throws RemoteException
  {
    for (int i = 0; i < userList.size(); i++)
    {
      if (userList.getUserByIndex(i).getUserID() == user.getUserID())
      {
        userList.getUserByIndex(i).setBio(bio);
        System.out.println("Bio set for" + user.getUsername());
      }
    }
  }

  @Override public void requestGame(User requester, Game game)
      throws RemoteException
  {
    for (int i = 0; i < userList.size(); i++)
    {
      if (userList.getUserByIndex(i).getUserID() == game.getUserId())
      {
        Rental rental = new Rental(userList.getUserByIndex(i), requester, game);
        rentalList.addRental(rental);
        System.out.println("Game added to rentals");
        propertyChangeSupport.firePropertyChange("newRental", null, rental);
        gameList.getGameById(game.getId()).setAvailable(false);
        getUserByGame(game).getGameList().getGameById(game.getId()).setAvailable(false);
        // What the fuck is that
//        i = userList.size();
      }
    }

  }

  @Override public void acceptGame(Rental rental) throws RemoteException
  {
    for (int i = 0; i < rentalList.size(); i++)
    {
      if (rentalList.getRentals().get(i).getId() == rental.getId())
      {
//        rentalList.getRentalById(i).setIsComplete(true);
        propertyChangeSupport.firePropertyChange("acceptGame", null, rentalList.getRentalById(i));
        userList.getUserByUserID(rental.getRequester().getUserID()).addToRented(rental.getGame());
        rentalList.removeRental(rental);
      }
    }
  }

  @Override public void declineGame(Rental rental) throws RemoteException
  {
    rentalList.removeRental(rental);
    gameList.getGameById(rental.getGame().getId()).setAvailable(true);
    getUserByGame(rental.getGame()).getGameList().getGameById(rental.getGame().getId()).setAvailable(true);
    propertyChangeSupport.firePropertyChange("declineGame", null, rental);
  }

  @Override public GameList getFullListOfGames() throws RemoteException
  {
    return gameList;
  }

  @Override public void addGame(Game game) throws RemoteException
  {
    gameList.addGame(game);
    userList.getUserByUserID(game.getUserId()).addGame(game);
    propertyChangeSupport.firePropertyChange("addGame", null, game);
  }

  @Override public void removeGame(Game game) throws RemoteException
  {
    getUserByGame(game).removeGame(game);
    gameList.removeGame(game);
    propertyChangeSupport.firePropertyChange("removeGame", null, game);
  }

  @Override public Game getGameByIndex(int index)
  {
    return gameList.getGame(index);
  }

  @Override public Game getGameByID(int gameID)
  {
    return gameList.getGameById(gameID);
  }

  @Override public User getUserByGame(Game game)
  {
    return userList.getUserByUserID(game.getUserId());
  }

  @Override public int getSizeOfGameList()
  {
    return gameList.size();
  }

  @Override
  public RentalList getRentalList() {
    return rentalList;
  }

  @Override public void registerUser(String username, String password)
  {
    userList.registerUser(username, password);
  }

  @Override
  public void setGameAvailableTrue(Game game) {
gameList.getGameById(game.getId()).setAvailable(true);
userList.getUserByUserID(game.getUserId()).getGameList().getGameById(game.getId()).setAvailable(true);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
}

