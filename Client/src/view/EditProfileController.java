package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import viewModel.EditProfileViewModel;
import viewModel.GameListViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class EditProfileController
{
  @FXML TextArea textArea;

  private Region root;

  private ViewHandler viewHandler;
  private EditProfileViewModel editProfileViewModel;

  public void init(ViewHandler viewHandler,
      EditProfileViewModel editProfileViewModel, Region root) throws RemoteException {
    this.root = root;
    this.viewHandler = viewHandler;
    this.editProfileViewModel = editProfileViewModel;
    this.textArea.setText(editProfileViewModel.getBio().getValue());
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML public void onCancel()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    viewHandler.openView("profile");
  }

  @FXML public void onSave()
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    editProfileViewModel.editBio(textArea.getText());
    viewHandler.openView("profile");
  }
}
