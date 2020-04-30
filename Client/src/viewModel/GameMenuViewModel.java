package viewModel;

import javafx.beans.property.*;
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
//  private StringProperty rentalPeriod;
  private ObjectProperty<LocalDate> fromDate;
  private ObjectProperty<LocalDate> toDate;
  private StringProperty availabilityPeriod;
  private BooleanProperty checkBox;

  private Model model;

  public GameMenuViewModel(Model model)
  {
    this.model = model;
    this.title = new SimpleStringProperty();
    this.type = new SimpleStringProperty();
    this.releaseYear = new SimpleStringProperty();
//    this.rentalPeriod = new SimpleStringProperty();
    this.fromDate = new SimpleObjectProperty<>();
    this.toDate = new SimpleObjectProperty<>();
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

//  public StringProperty getRentalPeriod()
//  {
//    return rentalPeriod;
//  }
  public ObjectProperty<LocalDate> getFromDate()
  {
    return fromDate;
  }

  public ObjectProperty<LocalDate> getToDate()
  {
    return toDate;
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
      boolean needsDeposit)
  {
    try
    {
      DateInterval rentalDate = new DateInterval(rentalFrom, rentalTo);

      int releaseYearInt = Integer.parseInt(releaseYear);
      int availabilityPeriodInt = Integer.parseInt(availablePeriod);

      Game game = new Game(name, type, releaseYearInt, needsDeposit, rentalDate,
          availabilityPeriodInt, model.getUserId());

      if (validateGame(game).equals("Success"))
      {
        model.AddGame(game);
      }
    }
    catch (Exception e)
    {
      System.out.println("Invalid operation.");
    }

  }

  public String validateGame(Game game)
  {
    String result = "";

    if (game.getTitle().equals(""))
      result += "Title should not be empty." + "\n";
    else if (game.getType().equals(""))
      result += "Type should not be empty" + "\n";
    else if (String.valueOf(game.getReleaseYear()).equals(""))
      result += "Release year should not be empty";
    else if (String.valueOf(game.getAvailabilityPeriod()).equals(""))
      result += "Availability period should not be empty";
    else if (
        game.getRentalPeriod().getStartDateObject().getTimeInMillis() > game
            .getRentalPeriod().getEndDateObject().getTimeInMillis() && (
            game.getRentalPeriod().getStartDate().equals("") || game
                .getRentalPeriod().getEndDate().equals("")))
      result += "Invalid dates" + "\n";
    else
      result = "Success";

    return result;
  }

  public Game getActualGame()
  {

    DateInterval dateInterval = new DateInterval(fromDate.get(), toDate.get());

    Game game = new Game(title.get(), type.get(),
        Integer.parseInt(releaseYear.get()), checkBox.get(), dateInterval,
        Integer.parseInt(availabilityPeriod.get()), model.getUserId());

    return game;
  }

  public void reset()
  {
    title.setValue("");
    type.setValue("");
    releaseYear.setValue("");
//    rentalPeriod.setValue("");
    toDate.setValue(null);
    fromDate.setValue(null);
    availabilityPeriod.setValue("");
    checkBox.setValue(false);
  }
}
