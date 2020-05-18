package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LoginModel;
import model.Model;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginViewModel
{
  private LoginModel model;
  private StringProperty loginUsername;
  private StringProperty loginPassword;
  private StringProperty registerUsername;
  private StringProperty registerPassword;

  public LoginViewModel(LoginModel model)
  {
    this.model = model;
    this.loginUsername = new SimpleStringProperty();
    this.loginPassword = new SimpleStringProperty();
    this.registerUsername = new SimpleStringProperty();
    this.registerPassword = new SimpleStringProperty();
  }

  public StringProperty getLoginUsername()
  {
    return loginUsername;
  }

  public StringProperty getLoginPassword()
  {
    return loginPassword;
  }

  public StringProperty getRegisterUsername()
  {
    return registerUsername;
  }

  public StringProperty getRegisterPassword()
  {
    return registerPassword;
  }

  public boolean exist(String username, String password) throws RemoteException
  {
   return model.login(username, password) != null;
  }
  public void setLocalUser() throws RemoteException {
    //model.setLocalUser(username.getValue());
    model.setLocalUserID(model.getLocalUserId());
  }
}
