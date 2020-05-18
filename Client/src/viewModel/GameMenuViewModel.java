package viewModel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.AddGameModel;
import model.DateInterval;
import model.Game;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.net.http.WebSocket;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GameMenuViewModel implements PropertyChangeListener
{
  private StringProperty title;
  private StringProperty type;
  private StringProperty releaseYear;
  private ObjectProperty<LocalDate> rentalFrom;
  private ObjectProperty<LocalDate> rentalTo;
  private StringProperty availabilityPeriod;
  private BooleanProperty checkBox;
  private StringProperty responseMessage;

  private AddGameModel model;

  public GameMenuViewModel(AddGameModel model)
  {
    this.model = model;
    this.title = new SimpleStringProperty();
    this.type = new SimpleStringProperty();
    this.releaseYear = new SimpleStringProperty();
    this.rentalFrom = new SimpleObjectProperty<>();
    this.rentalTo = new SimpleObjectProperty<>();
    this.availabilityPeriod = new SimpleStringProperty();
    this.checkBox = new SimpleBooleanProperty();
    this.responseMessage = new SimpleStringProperty();
    model.addListener(this);
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

  public ObjectProperty<LocalDate> getFromDate()
  {
    return rentalFrom;
  }

  public ObjectProperty<LocalDate> getToDate()
  {
    return rentalTo;
  }

  public StringProperty getAvailabilityPeriod()
  {
    return availabilityPeriod;
  }

  public BooleanProperty getCheckBox()
  {
    return checkBox;
  }

  public StringProperty getResponseMessage()
  {
    return responseMessage;
  }

  public void addCurrentGame() throws RemoteException
  {
    model
        .validateGame(title.getValue(), type.getValue(), releaseYear.getValue(),
            rentalFrom.getValue(), rentalTo.getValue(),
            availabilityPeriod.getValue(), checkBox.getValue());
  }

  public void reset()
  {
    title.setValue("");
    type.setValue("");
    releaseYear.setValue("");
    rentalTo.setValue(null);
    rentalFrom.setValue(null);
    availabilityPeriod.setValue("");
    checkBox.setValue(false);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    responseMessage.setValue((String) evt.getNewValue());
    System.out.println((String) evt.getNewValue());
  }
}
