package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.rmi.RemoteException;

public class LoadingScreenViewModel
{
  private Model model;
  private StringProperty username;
  private StringProperty password;

  public LoadingScreenViewModel(Model model)
  {
    this.model = model;
    this.username = new SimpleStringProperty();
    this.password = new SimpleStringProperty();
  }

  public StringProperty getUsername()
  {
    return username;
  }

  public StringProperty getPassword()
  {
    return password;
  }

  public boolean exist(String username) throws RemoteException
  {
    return model.getUser(username) != null;
  }
}
