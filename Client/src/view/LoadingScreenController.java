package view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.GameMenuViewModel;
import viewModel.LoadingScreenViewModel;

import java.rmi.RemoteException;

public class LoadingScreenController
{
  @FXML TextField username;
  @FXML TextField password;
  @FXML Label errorLabel;
  @FXML Button button;

  private Region root;
  private ViewHandler viewHandler;
  private LoadingScreenViewModel loadingScreenViewModel;

  public void init(ViewHandler viewHandler, LoadingScreenViewModel loadingScreenViewModel,
      Region root) {
    this.viewHandler = viewHandler;
    this.root = root;
    this.loadingScreenViewModel = loadingScreenViewModel;
    this.username.textProperty().bind(loadingScreenViewModel.getUsername());
    this.password.textProperty().bind(loadingScreenViewModel.getPassword());
  }

  public Region getRoot() {
    return root;
  }

  public void reset() {
    username.setText("");
    password.setText("");
  }

  @FXML public void onLogin() throws RemoteException
  {
    System.out.println(username.getText());
    if(loadingScreenViewModel.exist(username.getText())) viewHandler.openView("list");
    else errorLabel.setText("User does not exist");
  }

}
