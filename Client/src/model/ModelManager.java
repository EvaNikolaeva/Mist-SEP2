package model;

import mediator.GameListClient;
import mediator.GameListClientClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class ModelManager implements Model
{
  private User user;
  private String username;
  private String password;
  private PropertyChangeSupport property;
  private GameListClientClient gameListClientModel;
  private Game gameBuffer;

  public ModelManager() throws InterruptedException {
    this.property = new PropertyChangeSupport(this);
    this.username = "";
    this.password = "";
    this.gameListClientModel = new GameListClient(this);
  }

  @Override public void registerNewUser(String username, String password)
          throws RemoteException, SQLException {
    gameListClientModel.registerNewUser(username, password);
  }

  @Override public User login(String username, String password)
          throws RemoteException, SQLException {
    this.username = username;
    this.password = password;
    user = gameListClientModel.login(username, password);
    return gameListClientModel.login(username, password);
  }


  @Override public void clientAcceptIncomingGame(Rental rental)
          throws RemoteException, SQLException {
    gameListClientModel.clientAcceptIncomingGame(rental);
  }

  @Override public void clientDeclineIncomingGame(Rental rental)
          throws RemoteException, SQLException {
    gameListClientModel.clientDeclineIncomingGame(rental);
  }

  @Override public void clientRemoveGame(Game game) throws RemoteException, SQLException {
    gameListClientModel.clientRemoveGame(game);
  }

  @Override public GameList getAllGamesFromServer() throws RemoteException, SQLException {
    return gameListClientModel.getGamesFromServer();
  }

  @Override public void clientRequestGame(User requester, Game game)
          throws RemoteException, SQLException {
    gameListClientModel.clientRequestGame(requester, game);
  }

  @Override public User getUser(Game game) throws RemoteException, SQLException {
    return gameListClientModel.getUserFromServer(game);
  }

  @Override public void setGameBuffer(Game game)
  {
    gameBuffer = game;
  }

  @Override public Game getGameBuffer()
  {
    return gameBuffer;
  }

  @Override public String getUsername()
  {
    return username;
  }

  @Override public String getPassword()
  {
    return password;
  }

  @Override public void setBio(User user, String bioText) throws RemoteException, SQLException {
    gameListClientModel.clientSetBio(user, bioText);
  }

  //validate game is used to validate each field inserted in the view.
  //It checks that everything is not null and each field has content
  // and the logic of the dates makes sense in the real world
  //after all checks, a result is sent to through the view model to the view to continue the process
  //If the result is "Success" the game is added. Anything else will result in a pop up error
  //If the result is "Success" the game is added. Anything else will result in a pop up error
  //Also, the order each check is made makes sense programming wise.

  @Override public void clientAddGame(Game game) throws RemoteException, SQLException {
    gameListClientModel.clientAddGame(game);
  }

  @Override public void validateGame(String name, String type,
      String releaseYear, String availablePeriod, boolean needsDeposit)
          throws RemoteException, SQLException {

    //this is the check for everything not null. If the if is valid, which is not good,
    //the error message is sent

    String result = "";
    if (name == null || type == null || releaseYear == null
        || availablePeriod == null)
    {
      result += "All fields ought to be filled out!" + "\n";
      property.firePropertyChange("validateGame", null, result);
    }

    //In this else, the system checks for the empty fields and logic of the dates
    //If something fails, the error message is sent, if not, "success" is sent and the game is valid

    else
    {
      int releaseYearInt = 0;
      try
      {
        releaseYearInt = Integer.parseInt(releaseYear);
      }
      catch (Exception e)
      {
        result += "Release year has to a number" + "\n";
        property.firePropertyChange("validateGame", null, result);
      }
      int availablePeriodInt = 0;
      try
      {
        availablePeriodInt = Integer.parseInt(availablePeriod);
      }
      catch (Exception e)
      {
        result += "Availability period has to be a number" + "\n";
        property.firePropertyChange("validateGame", null, result);
      }

      //here, we created specific date object to help with the check and to also look good in the list

      Game game = new Game(name, type, releaseYearInt, needsDeposit,
          availablePeriodInt, user.getUserID());
      if (game.getTitle().equals(""))
      {
        result += "Title can't be empty." + "\n";
        property.firePropertyChange("validateGame", null, result);
      }
      else if (game.getType().equals(""))
      {
        result += "Type can't be empty." + "\n";
        property.firePropertyChange("validateGame", null, result);
      }
      else if (!(game.getReleaseYear() > 0) || !(game.getReleaseYear() < 3000))
      {
        result += "Release year has to be between 0 - 3000" + "\n";
        property.firePropertyChange("validateGame", null, result);
      }
      else if (!(game.getAvailabilityPeriod() > 0) || !(game.getAvailabilityPeriod() < 3652))
      {
        result +=
            "Availability period must be larger than a single day and less than 10 years." + "\n";
        property.firePropertyChange("validateGame", null, result);
      }
      else
      {
        result = "Success";
        clientAddGame(game);
        property.firePropertyChange("validateGame", null, result);
      }
    }
  }

  @Override public RentalList getRentalList() throws RemoteException, SQLException {
    return gameListClientModel.clientGetRentalList();
  }

  @Override public void setGameAvailabilityTrue(Game game)
          throws RemoteException, SQLException {
    gameListClientModel.setGameAvailableTrue(game);
  }

  @Override
  public GameList getAllRentedGames(User user) throws RemoteException, SQLException {
    return gameListClientModel.getRentedGames(user);
  }

  @Override
  public void clientRemoveUser(User user) throws RemoteException, SQLException
  {
    gameListClientModel.clientRemoveUser(user);
  }

  @Override
  public User getUser() throws RemoteException, SQLException {
    return user;
  }

  @Override
  public void gameAddedOnServer(Game game) throws RemoteException, SQLException {
    property.firePropertyChange("gameAdded", null, game);
  }
  @Override
  public void gameRemovedOnServer(Game game) throws RemoteException, SQLException {
    property.firePropertyChange("gameRemoved", null, game);
}

  @Override
  public void profileUpdate(User user) throws RemoteException, SQLException {
    property.firePropertyChange("gameRentalUpdate", null, user);
  }
  @Override
  public void gameAvailabilityUpdate(Game game) throws RemoteException, SQLException {
    property.firePropertyChange("gameAvailabilityChange", null, game);
  }

  @Override
  public void userRemovedOnServer(User user) throws RemoteException, SQLException
  {
    property.firePropertyChange("userRemoved", null, user);
  }

  @Override
  public User getUserByUserId(int userId) throws RemoteException, SQLException {
   return gameListClientModel.getUserById(userId);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }
}
