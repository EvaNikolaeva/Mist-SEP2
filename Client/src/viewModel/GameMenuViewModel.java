package viewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class GameMenuViewModel
{
  private StringProperty name;
  private StringProperty type;
  private StringProperty releaseYear;
  private StringProperty rentalPeriod;
  private StringProperty availabilityPeriod;
  private BooleanProperty checkBox;
  private Model model;

  public GameMenuViewModel(Model model)
  {
    this.model = model;
    this.name = new SimpleStringProperty();
    this.type = new SimpleStringProperty();
    this.releaseYear = new SimpleStringProperty();
    this.rentalPeriod = new SimpleStringProperty();
    this.availabilityPeriod = new SimpleStringProperty();
    this.checkBox = new SimpleBooleanProperty();
  }

  public StringProperty getName()
  {
    return name;
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

  public void reset()
  {
    name.setValue("");
    type.setValue("");
    releaseYear.setValue("");
    rentalPeriod.setValue("");
    availabilityPeriod.setValue("");
    checkBox.setValue(false);
  }
}
