package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.EditProfileModel;
import model.Model;

import java.rmi.RemoteException;

public class EditProfileViewModel
{
  private StringProperty bio;
  private EditProfileModel model;

  public EditProfileViewModel(EditProfileModel model)
  {
    this.model = model;
    this.bio = new SimpleStringProperty();
  }
  public StringProperty getBio() throws RemoteException {
    bio.setValue(model.getBio(model.getLocalUserId()));
    return bio;
  }
  public void editBio(String bio) throws RemoteException
  {
    model.setBio(model.getLocalUserId(), bio);
  }
}
