package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.EditProfileModel;
import model.Model;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class EditProfileViewModel
{
  private StringProperty bio;
  private EditProfileModel model;

  public EditProfileViewModel(EditProfileModel model)
  {
    this.model = model;
    this.bio = new SimpleStringProperty();
  }
  public StringProperty getBio() throws RemoteException, SQLException {
    bio.setValue(model.login(model.getUsername(), model.getPassword()).getBio());
    return bio;
  }
  public void editBio(String bio) throws RemoteException, SQLException {
    model.setBio(model.login(model.getUsername(), model.getPassword()), bio);
  }
}
