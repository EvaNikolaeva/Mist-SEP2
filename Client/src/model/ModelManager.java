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
import java.util.GregorianCalendar;

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
    String result = "";
   if(name == null || type == null || releaseYear == null || rentalFrom == null || rentalTo == null || availablePeriod == null){
     result += "All fields ought to be filled out!" + "\n";
     property.firePropertyChange("validateGame", null, result);
   }
   else{
     int releaseYearInt = 0;
     try {
       releaseYearInt = Integer.parseInt(releaseYear);
     }
     catch (Exception e){
       result += "Release year has to a number" + "\n";
       property.firePropertyChange("validateGame", null, result);
     }
     int availablePeriodInt = 0;
     try {
       availablePeriodInt = Integer.parseInt(availablePeriod);
     }
     catch (Exception e){
       result += "Availability period has to be a number" + "\n";
       property.firePropertyChange("validateGame", null, result);
     }
     DateInterval dateInterval = new DateInterval(rentalFrom, rentalTo);
     Calendar rightNow = Calendar.getInstance();
     Game game = new Game(name, type, releaseYearInt, needsDeposit, dateInterval, availablePeriodInt, user.getUserID());
     if(game.getRentalPeriod().getStartDateObject().getTimeInMillis() < Calendar.getInstance().getTimeInMillis()){
       result += "Start date can't be in the past." + "\n";
       property.firePropertyChange("validateGame", null, result);
     }
     else if(game.getRentalPeriod().getStartDateObject().getTimeInMillis() > game.getRentalPeriod().getEndDateObject().getTimeInMillis()){
       result += "Start date has to be before the end date." + "\n";
       property.firePropertyChange("validateGame", null, result);
     }
     else if(game.getTitle().equals("")){
       result += "Title can't be empty." + "\n";
       property.firePropertyChange("validateGame", null, result);
     }
     else if(game.getType().equals("")){
       result += "Type can't be empty." + "\n";
       property.firePropertyChange("validateGame", null, result);
     }
     else if(!(game.getReleaseYear() > 0)){
       result += "Release year should be after the birth of Christ." + "\n";
       property.firePropertyChange("validateGame", null, result);
     }
     else if(!(game.getAvailabilityPeriod() > 0)){
       result += "Availability period must be larger than a single day." + "\n";
       property.firePropertyChange("validateGame", null, result);
     }
     else{
       result = "Success";
       AddGame(game);
       property.firePropertyChange("validateGame", null, result);
     }
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
