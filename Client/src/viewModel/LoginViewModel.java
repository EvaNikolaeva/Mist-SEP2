package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LoginModel;
import model.Model;
import model.User;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginViewModel
{
  private LoginModel model;
  private StringProperty loginUsername;
  private StringProperty loginPassword;

  public LoginViewModel(LoginModel model)
  {
    this.model = model;
    this.loginUsername = new SimpleStringProperty();
    this.loginPassword = new SimpleStringProperty();
  }

  public StringProperty getLoginUsername()
  {
    return loginUsername;
  }

  public StringProperty getLoginPassword()
  {
    return loginPassword;
  }

  public boolean exist(String username, String password) throws RemoteException
  {
    return model.login(username, password) != null;
  }
  public void setLocalUser() throws RemoteException {
    //model.setLocalUser(username.getValue());
    User user1 = model.login(loginUsername.getValue(), loginPassword.getValue());
    model.setLocalUserID(user1.getUserID());
  }
}
