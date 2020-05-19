package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.LoginViewModel;

public class LoginScreenController
{
  @FXML TextField loginUsername;
  @FXML TextField loginPassword;
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

  @FXML public void onLogin()
  {
    loginUsername.setText(loginUsername.getText());
    loginPassword.setText(loginPassword.getText());
    Platform.runLater(() -> {
      try
      {
        if (!loginViewModel
            .exist(loginUsername.getText(),
                loginPassword.getText()))
        {
          errorLabel.setText("User does not exist");
        }
        else if (loginUsername.getText().equals("") || loginPassword.getText()
            .equals(""))
        {
          errorLabel.setText("Fields should not be empty");
        }
        else
        {
          viewHandler.openView("list");
          loginViewModel.setLocalUser();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    });
  }

  @FXML public void onRegister()
  {
    //    Platform.runLater(() -> {           CATA: how is this different from register?
    //      try
    //      {
    //        if (loginViewModel
    //            .exist(loginUsername.getText(), loginPassword.getText()))
    //        {
    //          errorLabel.setText("Username already used");
    //        }
    //        else if (loginUsername.getText().equals("") || loginPassword.getText()
    //            .equals(""))
    //        {
    //          errorLabel.setText("Fields should not be empty");
    //        }
    //        else
    //        {
    //          viewHandler.openView("list");
    //          loginViewModel.setLocalUser();
    //        }
    //      }
    //      catch (Exception e)
    //      {
    //        e.printStackTrace();
    //      }
    //    });
  }
}

