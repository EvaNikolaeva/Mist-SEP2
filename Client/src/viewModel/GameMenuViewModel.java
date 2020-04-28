package viewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

public void addGame(String name, String type, String releaseYear, LocalDate rentalFrom, LocalDate rentalTo, LocalDate availableFrom, LocalDate availableTo, boolean needsDeposit) throws RemoteException {
  GregorianCalendar rentalFromDateCalendar = GregorianCalendar.from(rentalFrom.atStartOfDay(ZoneId.systemDefault()));
  GregorianCalendar rentalToDateCalendar = GregorianCalendar.from(rentalTo.atStartOfDay(ZoneId.systemDefault()));
  GregorianCalendar availableFromDateCalendar = GregorianCalendar.from(availableFrom.atStartOfDay(ZoneId.systemDefault()));
  GregorianCalendar availableToDateCalendar = GregorianCalendar.from(availableTo.atStartOfDay(ZoneId.systemDefault()));
  DateInterval availableDate = new DateInterval(availableFromDateCalendar, availableToDateCalendar);
  DateInterval rentalDate = new DateInterval(rentalFromDateCalendar, rentalToDateCalendar);
  int releaseYearInt = Integer.parseInt(releaseYear);
  Game game = new Game(name, type, releaseYearInt, needsDeposit, rentalDate, availableDate, model.getUserId());
  model.AddGame(game);
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
