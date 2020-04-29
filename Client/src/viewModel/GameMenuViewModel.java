package viewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.DateInterval;
import model.Game;
import model.Model;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GameMenuViewModel
{
  private StringProperty title;
  private StringProperty type;
  private StringProperty releaseYear;
  private StringProperty rentalPeriod;
  private StringProperty availabilityPeriod;
  private BooleanProperty checkBox;

  private Model model;

  public GameMenuViewModel(Model model)
  {
    this.model = model;
    this.title = new SimpleStringProperty();
    this.type = new SimpleStringProperty();
    this.releaseYear = new SimpleStringProperty();
    this.rentalPeriod = new SimpleStringProperty();
    this.availabilityPeriod = new SimpleStringProperty();
    this.checkBox = new SimpleBooleanProperty();
  }

  public StringProperty getName()
  {
    return title;
  }

  public StringProperty getType()
  {
    return type;
  }

  public StringProperty getReleaseYear()
  {
    return releaseYear;
  }

  public StringProperty getRentalPeriod()
  {
    return rentalPeriod;
  }

  public StringProperty getAvailabilityPeriod()
  {
    return availabilityPeriod;
  }

  public BooleanProperty getCheckBox()
  {
    return checkBox;
  }

  public void addGame(String name, String type, String releaseYear,
      LocalDate rentalFrom, LocalDate rentalTo, String availablePeriod,
      boolean needsDeposit) throws RemoteException
  {
    try
    {
      GregorianCalendar rentalFromDateCalendar = GregorianCalendar      //make the check for this in
                                                                        // validate method
          .from(rentalFrom.atStartOfDay(ZoneId.systemDefault()));
      GregorianCalendar rentalToDateCalendar = GregorianCalendar
          .from(rentalTo.atStartOfDay(ZoneId.systemDefault()));
      DateInterval rentalDate = new DateInterval(rentalFromDateCalendar,    //these here are fine, not spaghetti
          rentalToDateCalendar);

      int releaseYearInt = Integer.parseInt(releaseYear);
      int availabilityPeriodInt = Integer.parseInt(availablePeriod);

      Game game = new Game(name, type, releaseYearInt, needsDeposit, rentalDate,
          availabilityPeriodInt, model.getUserId());

      if (validateGame(game).equals(
          "Success"))  //we can move everything from controller on submit, here
      {
        model.AddGame(game);
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.ERROR, validateGame(game),
            //move to view
            ButtonType.OK);
        alert.showAndWait();
        alert.close();
      }
    }
    catch (Exception e)       //all alerts have to be in view. always
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Enter a valid release year/availability period.", ButtonType.OK);
      alert.showAndWait();
      alert.close();
    }
  }

  public String validateGame(Game game)
  {
    String result = "";

    if (game.getTitle().equals(""))
      result += "Title should not be empty." + "\n";
    else if (game.getType().equals(""))                 //this is good here. add all of the validations here
      result += "Type should not be empty" + "\n";
    else if (String.valueOf(game.getReleaseYear()).equals(""))
      result += "Release year should not be empty";
    else if (String.valueOf(game.getAvailabilityPeriod()).equals(""))
      result += "Availability period should not be empty";
    else if (
        game.getRentalPeriod().getStartDateObject().getTimeInMillis() > game
            .getRentalPeriod().getEndDateObject().getTimeInMillis() && (
            game.getRentalPeriod().getStartDate() == null
                || game.getRentalPeriod().getEndDate() == null))
      result += "Invalid dates" + "\n";
    else
      result = "Success";

    return result;
  }

  public void reset()
  {
    title.setValue("");
    type.setValue("");
    releaseYear.setValue("");
    rentalPeriod.setValue("");
    availabilityPeriod.setValue("");
    checkBox.setValue(false);
  }
}
