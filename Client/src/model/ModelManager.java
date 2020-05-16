package model;

import Utility.UnnamedPropertyChangeSubject;
import mediator.GameListClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ModelManager implements Model
{
  private User user;
  private Game game;
  private PropertyChangeSupport property;

  public ModelManager()
      throws RemoteException, MalformedURLException, InterruptedException,
      NotBoundException
  {
   this.property = new PropertyChangeSupport(this);
  }


  //validate game is used to validate each field inserted in the view.
  //It checks that everything is not null and each field has content
  // and the logic of the dates makes sense in the real world
  //after all checks, a result is sent to through the view model to the view to continue the process
  //If the result is "Success" the game is added. Anything else will result in a pop up error
  //Also, the order each check is made makes sense programming wise.

  @Override public void registerNewUser(String username, String password)
  {

  }

  @Override public User login(String username, String password)
  {
    return null;
  }

  @Override public ArrayList<Integer> getAllAvailableGames()
  {
    return null;
  }

  @Override public ArrayList<Integer> getAllPendingGames()
  {
    return null;
  }

  @Override public ArrayList<Integer> getAllUserOwnedGames()
  {
    return null;
  }

  @Override public ArrayList<Integer> getAllUserPendingGames()
  {
    return null;
  }

  @Override public ArrayList<Integer> getAllUserRentedGames()
  {
    return null;
  }

  @Override public ArrayList<Integer> getAllUserIncomingGames()
  {
    return null;
  }

  @Override public void requestGame(int userID, int gameID)
  {

  }

  @Override public String getUsername(int userID)
  {
    return null;
  }

  @Override public String getBio(int userID)
  {
    return null;
  }

  @Override public void removeGame(int userID, int gameID)
  {

  }

  @Override public void setBio(int userBio, String bio)
  {

  }

  @Override public void acceptIncomingGame(int userID, int gameID)
  {

  }

  @Override public void declineIncomingGame(int userID, int gameID)
  {

  }

  @Override public void addGame(Game game)
  {

  }

  @Override public void validateGame(String name, String type,
      String releaseYear, LocalDate rentalFrom, LocalDate rentalTo,
      String availablePeriod, boolean needsDeposit)
  {

    //this is the check for everything not null. If the if is valid, which is not good,
    //the error message is sent

    String result = "";
    if (name == null || type == null || releaseYear == null
        || rentalFrom == null || rentalTo == null || availablePeriod == null)
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

      DateInterval dateInterval = new DateInterval(rentalFrom, rentalTo);
      Calendar rightNow = Calendar.getInstance();
      Game game = new Game(name, type, releaseYearInt, needsDeposit,
          dateInterval, availablePeriodInt, user.getUserID());
      if (game.getRentalPeriod().getStartDateObject().getTimeInMillis()
          < Calendar.getInstance().getTimeInMillis())
      {
        result += "Start date can't be in the past." + "\n";
        property.firePropertyChange("validateGame", null, result);
      }
      else if (game.getRentalPeriod().getStartDateObject().getTimeInMillis()
          > game.getRentalPeriod().getEndDateObject().getTimeInMillis())
      {
        result += "Start date has to be before the end date." + "\n";
        property.firePropertyChange("validateGame", null, result);
      }
      else if (game.getTitle().equals(""))
      {
        result += "Title can't be empty." + "\n";
        property.firePropertyChange("validateGame", null, result);
      }
      else if (game.getType().equals(""))
      {
        result += "Type can't be empty." + "\n";
        property.firePropertyChange("validateGame", null, result);
      }
      else if (!(game.getReleaseYear() > 0))
      {
        result += "Release year should be after the birth of Christ." + "\n";
        property.firePropertyChange("validateGame", null, result);
      }
      else if (!(game.getAvailabilityPeriod() > 0))
      {
        result +=
            "Availability period must be larger than a single day." + "\n";
        property.firePropertyChange("validateGame", null, result);
      }
      else
      {
        result = "Success";
        addGame(game);
        property.firePropertyChange("validateGame", null, result);
      }
    }
  }

  @Override public User getOtherUserByID(int userID)
  {
    return null;
  }

  @Override public ArrayList<Integer> getOtherAllUserOwnedGames(int userID)
  {
    return null;
  }

  @Override public ArrayList<Integer> getOtherAllUserPendingGames(int userID)
  {
    return null;
  }

  @Override public void setLocalUserID(int userID)
  {

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
