package model;

import Utility.UnnamedPropertyChangeSubject;
import mediator.GameListClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class ModelManager implements Model
{
  private GameListClient client;
  private User user;
  private PropertyChangeSupport property;

  public ModelManager()
      throws RemoteException, MalformedURLException, NotBoundException,
      InterruptedException
  {
    this.client = new GameListClient(this);
    this.user = new User("Testy", 123456);
    this.property = new PropertyChangeSupport(this);
    updateUserGames();
  }


  @Override public void AddGame(Game game) throws RemoteException
  {

      for(int i = 0;i<client.getGameList().size();i++)
      {
        if(game.getId() == client.getGameList().getGame(i).getId())
        {
          game.reRollID();
        }
      }
    client.addGame(game);
    user.getGames().addGame(game);
  }

  @Override public void RemoveGame(int id) throws RemoteException
  {
    client.removeGame(id);
    user.getGames().removeGame(id);
  }

  @Override public GameList GetGameList() throws RemoteException
  {
    return client.getGameList();
  }

  @Override public GameList getUserGamesList()
  {
    return user.getGames();
  }

  @Override public void updateUserGames() throws RemoteException
  {
    GameList gameList = GetGameList();
    for (int i = 0; i < gameList.size(); i++)
    {
      if (gameList.getGame(i).getUserID() == user.getUserID())
      {
        user.getGames().addGame(gameList.getGame(i));
      }
    }
  }

  @Override public int getUserId()
  {
    return user.getUserID();
  }

  @Override
  public void validateGame(String name, String type, String releaseYear,
                             LocalDate rentalFrom, LocalDate rentalTo, String availablePeriod,
                             boolean needsDeposit) throws RemoteException {
    int workingReleaseYear;
    int workingAvailabilityPeriodInt;
    DateInterval workingRentalDate;


  try {
    workingRentalDate = new DateInterval(rentalFrom, rentalTo);

      workingReleaseYear = Integer.parseInt(releaseYear);
    workingAvailabilityPeriodInt = Integer.parseInt(availablePeriod);




    } catch (Exception e) {
    workingReleaseYear = 0;
    workingAvailabilityPeriodInt = 0;
    LocalDate date1 = LocalDate.of(1, 1, 1);
    LocalDate date2 = LocalDate.of(1, 1, 1);
    workingRentalDate = new DateInterval(date1, date2);
    }


    Game game = new Game(name, type, workingReleaseYear, needsDeposit, workingRentalDate,
            workingAvailabilityPeriodInt, getUserId());


    String result = "";

    if (name.equals("")) {
      result += "Title should not be empty." + "\n";
      property.firePropertyChange("validateGame", null, result);
    }
    else if (type.equals("") || game.getType() == null) {
      result += "Type should not be empty" + "\n";
      property.firePropertyChange("validateGame", null, result);
    }
    else if (String.valueOf(game.getReleaseYear()).equals("0")) {
      result += "Release year should not be empty";
      property.firePropertyChange("validateGame", null, result);
    }
    else if (String.valueOf(game.getAvailabilityPeriod()).equals("0")) {
      result += "Availability period should not be empty";
      property.firePropertyChange("validateGame", null, result);
    }
    else if (
            game.getRentalPeriod().getStartDateObject().getTimeInMillis() > game
                    .getRentalPeriod().getEndDateObject().getTimeInMillis() && (
                    game.getRentalPeriod().getStartDate().equals("") || game
                            .getRentalPeriod().getEndDate().equals(""))) {
      result += "Invalid dates" + "\n";
      property.firePropertyChange("validateGame", null, result);
    }
    else {
      AddGame(game);
      result = "Success";
      property.firePropertyChange("validateGame", null, result);
    }
  }

  @Override
  public void addListener(PropertyChangeListener listener) {
    property.addPropertyChangeListener(listener);
  }

  @Override
  public void removeListener(PropertyChangeListener listener) {
    property.addPropertyChangeListener(listener);
  }
}
