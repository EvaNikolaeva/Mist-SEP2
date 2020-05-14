package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.rmi.RemoteException;

public class EditProfileViewModel
{
  private StringProperty text;
  private Model model;

  public EditProfileViewModel(Model model)
  {
    this.model = model;
    this.text = new SimpleStringProperty();
  }
  public StringProperty getText() throws RemoteException {
    text.setValue(model.getLocalUser().getBio());
    return text;
  }
  public void editMessage(String message) throws RemoteException
  {
    System.out.println(model.getLocalUser());
    model.getLocalUser().setBio(message);
    model.setUserBio(model.getLocalUser(), message);
  }
}
