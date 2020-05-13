package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class EditProfileViewModel
{
  private StringProperty text;
  private Model model;

  public EditProfileViewModel(Model model)
  {
    this.model = model;
    this.text = new SimpleStringProperty();
  }

  public StringProperty getText()
  {
    return text;
  }

  public void editMessage(String message)
  {
    this.text.setValue(message);
  }

  public void reset()
  {
    this.text.setValue("");
  }
}
