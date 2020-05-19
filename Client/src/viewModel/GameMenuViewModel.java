package viewModel;


import javafx.beans.property.*;
import model.AddGameModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.time.LocalDate;


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
            availabilityPeriod.getValue(), checkBox.getValue());
  }

  public void reset()
  {
    title.setValue("");
    type.setValue("");
    releaseYear.setValue("");
    availabilityPeriod.setValue("");
    checkBox.setValue(false);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    responseMessage.setValue((String) evt.getNewValue());
    System.out.println((String) evt.getNewValue());
  }
}
