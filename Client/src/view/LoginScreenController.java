package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.User;
import viewModel.LoginViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class LoginScreenController
{
  @FXML TextField loginUsername;
  @FXML
  PasswordField loginPassword;
  @FXML Label errorLabel;

  private Region root;
  private ViewHandler viewHandler;
  private LoginViewModel loginViewModel;

  public void init(ViewHandler viewHandler, LoginViewModel loginViewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.loginViewModel = loginViewModel;
    this.errorLabel.setText("");

    this.loginUsername.setText("");
    this.loginPassword.setText("");

    //    this.loginUsername.textProperty()
    //        .bindBidirectional(loginViewModel.getLoginUsername());
    //    this.loginPassword.textProperty()
    //        .bindBidirectional(loginViewModel.getLoginPassword());  ????????????????????????????????
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset()
  {
    loginUsername.setText("");
    loginPassword.setText("");
    errorLabel.setText("");
  }

  @FXML public void onLogin() throws RemoteException, InterruptedException, NotBoundException, MalformedURLException {

    Platform.runLater(() -> {
      User userBuffer = null;
      try {
        userBuffer = loginViewModel.login(loginUsername.getText(), loginPassword.getText());
      } catch (RemoteException | SQLException e) {
        e.printStackTrace();
      }
      if(userBuffer != null){

        try {
          viewHandler.openView("list");
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      else{
        errorLabel.setText("Invalid username or password.");
      }

    });

  }

  @FXML public void onRegister() throws RemoteException, SQLException {
    loginViewModel.registerUser(loginUsername.getText(), loginPassword.getText());
    errorLabel.setText("User created if username was not taken. You can now try to log in.");
  }
}

